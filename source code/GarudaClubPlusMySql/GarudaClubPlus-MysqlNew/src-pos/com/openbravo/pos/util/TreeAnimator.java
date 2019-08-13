/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.util;

/**
 *
 * @author swathi
 */
/* Copyright (c) 2006 Timothy Wall All Rights Reserved */
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.util.Timer;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;

/**
 * Animates moving tree cells out of the way for a potential drop. This
 * decorator completely over-paints the target JTree, optionally
 * painting a dragged item and animating creation of a space for the
 * dragged item to be dropped.
 */
// TODO: adjust horizontal position based on the parent
// TODO: spring-loaded folders: how to drop on/before
// TODO: un-spring folders: collapsing a previous folder is disruptive
// TODO: change graphics to left of node (e.g. if next-to-last becomes last)
// TODO: animate when dropped (instead of insta-place)
// TODO: animate canceled drops (slide dragged object back to its home)
//
public class TreeAnimator extends AbstractComponentDecorator implements TreeExpansionListener {

    private static final boolean SPRING = false;
    protected class TargetLocation {
        /** Path to new parent. */
        public TreePath parentPath;
        /** Index within new parent. */
        public int index;
        /** Actual visible row in the tree. */
        public int insertionRow;

        public TargetLocation(TreePath path, int i, int row) {
            this.parentPath = path;
            this.index = i;
            this.insertionRow = row;
        }
        public String toString() {
            return parentPath.toString() + ":" + index + " (" + insertionRow + ")";
        }
    }

    /**
     * Animation repaint interval. Make this larger to slow down the
     * animation.
     */
    private static final int INTERVAL = 1000 / 24;
    private static Timer timer = new Timer(true);

    /** Simple decorator to provide the ghosted drag image. */
    private final class GhostedDragImage extends AbstractComponentDecorator {
        private TreePath path;
        private Point location;
        private Point offset;

        public GhostedDragImage(TreePath path, Point origin) {
            super(tree, JLayeredPane.DRAG_LAYER.intValue());
            this.path = path;
            Rectangle b = tree.getPathBounds(path);
            location = origin;
            this.offset = new Point(origin.x - b.x, origin.y - b.y);
        }

        public void setLocation(Point where, TreePath parentPath) {
            this.location = new Point(where);
            Rectangle b = tree.getPathBounds(path);
            Rectangle lastRow = tree.getRowBounds(tree.getRowCount()-1);
            int height = lastRow.y + lastRow.height;
            Point origin = new Point(b.x, location.y - offset.y);
            // Select a horizontal offset appropriate for a child of the
            // given parent path
            if (parentPath != null) {
                int count = path.getPathCount();
                if (!tree.isRootVisible() || !tree.getShowsRootHandles())
                    --count;
                Insets insets = tree.getInsets();
                int delta = (origin.x - (insets != null ? insets.left : 0)) / count;
                b = tree.getPathBounds(parentPath);
                origin.x = b.x + delta;
            }
            location.x = origin.x;
            location.y = Math.max(0, origin.y);
            location.y = Math.min(location.y, height - b.height);
            getPainter().repaint();
        }

        public Point getLocation() {
            return location;
        }

        public void paint(Graphics g) {
            Rectangle b = tree.getPathBounds(path);
            g = g.create(location.x, location.y, b.width, b.height);
            ((Graphics2D)g).translate(-b.x, -b.y);
            ((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                                                                    0.5f));
            tree.paint(g);
        }
    }
    private final class Counter extends TimerTask {
        public boolean painted;
        public synchronized void painted() {
            painted = true;
        }
        public void run() {
            if (painted && reposition()) {
                synchronized(this) {
                    painted = false;
                    repaint();
                }
            }
        }
    }

    private Counter counter;
    /** Index of insertion point. */
    private int insertionRow = -1;
    /** Object being dragged, if any. */
    private TreePath draggedPath;
    /** Current spring-opened paths. */
    private Set springs = new HashSet();
    private JTree tree;
    private Map bounds = new HashMap();
    private GhostedDragImage dragImage;
    private Point origin;
    private boolean dragActive;

    public TreeAnimator(final JTree tree) {
        super(tree);
        this.tree = tree;
    }

    /** The default assumes any node except the root may be moved. */
    protected boolean canMove(TreePath path) {
        Object o = path.getLastPathComponent();
        //return !o.equals(tree.getModel().getRoot());
        return false;
    }

    /**
     * Returns whether node at the given path may be moved to the given
     * index on the given target path.  The default disallows moves
     * if the target is a descendent of the moved path.
     */
    protected boolean canMove(TreePath fromPath, TreePath toPath, int index) {
       /* if (canMove(fromPath)) {
            TreePath testPath = toPath;
            while (testPath != null) {
                if (testPath.equals(fromPath))
                    return false;
                testPath = testPath.getParentPath();
            }
            return true;
        }*/
        return false;
    }

    /**
     * Request that the node on the given path be moved to the given
     * index on the given target path. If toPath is the parent path to
     * fromPath, then the index represents the insertion index
     * <em>after</em> the object is removed from its current index.
     */
    protected void move(TreePath fromPath, TreePath toPath, int index) {
        Object moved = fromPath.getLastPathComponent();
        Object movedTo = toPath.getLastPathComponent();
        if (tree.getModel() instanceof DefaultTreeModel
            && moved instanceof MutableTreeNode
            && movedTo instanceof MutableTreeNode) {
            DefaultTreeModel treeModel = (DefaultTreeModel)tree.getModel();
            MutableTreeNode child = (MutableTreeNode)moved;
            MutableTreeNode newParent = (MutableTreeNode)movedTo;
            treeModel.removeNodeFromParent(child);
            treeModel.insertNodeInto(child, newParent, index);
        }
        else {
            throw new UnsupportedOperationException("You must override move()");
        }
    }

    /** Start an internal drag. Returns whether the drag is started. */
    public boolean startDrag(Point where) {
       /* draggedPath = tree.getPathForLocation(where.x, where.y);
        if (draggedPath != null && canMove(draggedPath)) {
            dragActive = true;
            tree.collapsePath(draggedPath);
            origin = where;
            insertionRow = tree.getRowForPath(draggedPath);
            dragImage = new GhostedDragImage(draggedPath, origin);
            return true;
        }*/
        return false;
    }

    public void treeExpanded(TreeExpansionEvent e) {
        int oldRowCount = bounds.size();
        int rows = tree.getRowCount();
        int start = tree.getRowForPath(e.getPath()) + 1;
        Rectangle rect = tree.getPathBounds(e.getPath());
        for (int i=0;i < rows - oldRowCount;i++) {
            TreePath path = tree.getPathForRow(start + i);
            bounds.put(path, new Rectangle(rect));
        }
        repaint();
    }

    public void treeCollapsed(TreeExpansionEvent e) {
        for (Iterator i=bounds.keySet().iterator();i.hasNext();) {
            TreePath path = (TreePath)i.next();
            if (tree.getRowForPath(path) == -1) {
                i.remove();
            }
        }
        repaint();
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            tree.addTreeExpansionListener(this);
            int size = tree.getRowCount();
            for (int i = 0; i < size; i++) {
                TreePath path = tree.getPathForRow(i);
                bounds.put(path, getProjectedPathBounds(path));
            }
            counter = new Counter();
            timer.schedule(counter, INTERVAL, INTERVAL);
        }
        else {
            tree.removeTreeExpansionListener(this);
            bounds.clear();
            if (counter != null) {
                counter.cancel();
                counter = null;
            }
        }
    }

    /** End an internal drag. */
    public void endDrag(Point where) {
        if (!dragActive)
            throw new IllegalStateException("Not dragging");
        TargetLocation loc = getTargetLocation(where);
        int draggedRow = tree.getRowForPath(draggedPath);
        dragImage.dispose();
        dragImage = null;
        insertionRow = -1;
        if (loc != null && loc.insertionRow != -1 && loc.insertionRow != draggedRow) {
            move(draggedPath, loc.parentPath, loc.index);
            bounds.remove(draggedPath);
        }
        draggedPath = null;
        dragActive = false;
    }

    public void dispose() {
        tree.removeTreeExpansionListener(this);
        super.dispose();
    }

    private boolean reposition() {
        boolean changed = false;
        for (Iterator i = bounds.keySet().iterator(); i.hasNext();) {
            TreePath path = (TreePath)i.next();
            Rectangle current = (Rectangle)bounds.get(path);
            if (current == null) {
                System.err.println("warning: no current bounds for " + path);
                i.remove();
                continue;
            }
            Rectangle end = getProjectedPathBounds(path);
            if (end == null) {
                System.err.println("warning: no final bounds for " + path);
                i.remove();
                continue;
            }
            if (current.x != end.x || current.y != end.y) {
                int xdelta = (end.x - current.x) / 2;
                int ydelta = (end.y - current.y) / 2;
                if (xdelta == 0)
                    current.x = end.x;
                else
                    current.x += xdelta;
                if (ydelta == 0)
                    current.y = end.y;
                else
                    current.y += ydelta;
                bounds.put(path, current);
                changed = true;
            }
        }

        return changed;
    }

    /** Return a proposed insertion location for the given coordinate given in
     * actual JTree coordinate space.
     * Returns null if no insertion is allowed at the given location.
     */
    protected TargetLocation getTargetLocation(Point where) {
        int x = where.x;
        int y = where.y;
        int size = tree.getRowCount();
        Rectangle appendBounds = tree.getRowBounds(size - 1);
        appendBounds.y += appendBounds.height;
        appendBounds.height = 0;
        int targetRow = tree.getClosestRowForLocation(x, y);
        int draggedRow = tree.getRowForPath(draggedPath);
        TreePath parentPath = null;
        int index = 0;
        if (targetRow == draggedRow) {
            parentPath = draggedPath.getParentPath();
            Object parent = parentPath.getLastPathComponent();
            Object node = draggedPath.getLastPathComponent();
            index = tree.getModel().getIndexOfChild(parent, node);
        }
        else if (targetRow == 0) {
            // Can't insert above root
            if (tree.isRootVisible()) {
                return null;
            }
            // Insert first at root
            parentPath = new TreePath(tree.getModel().getRoot());
            index = 0;
        }
        else {
            // The actual path at the target row will be pushed down
            // one slot. Use that path's parent.
            TreePath pathUnderPoint = tree.getPathForRow(targetRow);
            if (pathUnderPoint == null)
                return null;
            for (Iterator i=springs.iterator();i.hasNext();) {
                TreePath p = (TreePath)i.next();
                if (!p.isDescendant(pathUnderPoint)) {
                    i.remove();
                    tree.collapsePath(p);
                }
            }
            Object node = pathUnderPoint.getLastPathComponent();
            boolean underIsLeaf = tree.getModel().isLeaf(node);
            if (underIsLeaf) {
                parentPath = pathUnderPoint.getParentPath();
                Object parent = parentPath.getLastPathComponent();
                index = tree.getModel().getIndexOfChild(parent, node);
                // TODO: adjust parent when at the end of a list of items
                // probably based on horizontal position
            }
            else if (draggedRow == targetRow - 1) {
                parentPath = pathUnderPoint;
                index = 0;
            }
            else {
                parentPath = pathUnderPoint.getParentPath();
                index = tree.getModel().getIndexOfChild(parentPath.getLastPathComponent(),
                                                        pathUnderPoint.getLastPathComponent());
                // TODO: if moving down into a collapsed non-leaf,
                // spring-expand it
                // TODO: if moving up out of a spring-expanded non-leaf,
                // collapse it
                // DONT: collapse spring-expanded if moving down
            }
        }
        return new TargetLocation(parentPath, index, targetRow);
    }

    /** Invoke this method as the cursor location changes. */
    public void setInsertionLocation(Point where) {
        if (!dragActive)
            throw new IllegalStateException("Not dragging");
        // Avoid painting focus and/or selection bgs, kind of a hack
        getPainter().requestFocus();
        tree.clearSelection();
        // end hack
        TargetLocation loc = getTargetLocation(where);
        TreePath parentPath = null;
        if (loc != null && draggedPath != null) {
            if (canMove(draggedPath, loc.parentPath, loc.index)) {
                parentPath = loc.parentPath;
                setInsertionRow(loc.insertionRow);
            }
        }
        dragImage.setLocation(where, parentPath);
    }

    protected int getInsertionRow() { return insertionRow; }

    private void setInsertionRow(int idx) {
        if (idx != insertionRow) {
            insertionRow = idx;
            repaint();
        }
    }

    private Rectangle getProjectedPathBounds(TreePath path) {
        Rectangle pathBounds = tree.getPathBounds(path);
        if (draggedPath != null) {
            int row = tree.getRowForPath(path);
            int removalRow = tree.getRowForPath(draggedPath);
            Rectangle draggedBounds = tree.getPathBounds(draggedPath);
            if (removalRow < row && row <= insertionRow) {
                pathBounds.y -= draggedBounds.height;
            }
            else if (insertionRow <= row && row < removalRow) {
                pathBounds.y += draggedBounds.height;
            }
        }
        return pathBounds;
    }

    private Rectangle getCurrentCellBounds(TreePath path) {
        Rectangle after = getProjectedPathBounds(path);
        Rectangle current = (Rectangle)bounds.get(path);
        if (current != null) {
            after.x = current.x;
            after.y = current.y;
        }
        return after;
    }

    public void paint(Graphics g) {
        boolean db = tree.isDoubleBuffered();
        tree.setDoubleBuffered(false);
        try {
            Rectangle b = getDecorationBounds();
            g.setColor(tree.getBackground());
            g.fillRect(b.x, b.y, b.width, b.height);
            int prevIndex = -1;
            Rectangle prevBounds = null;
            for (int i = 0; i < tree.getRowCount(); i++) {
                TreePath path = tree.getPathForRow(i);
                if (path.equals(draggedPath))
                    continue;
                Rectangle r = getCurrentCellBounds(tree.getPathForRow(i));
                Rectangle r2 = tree.getRowBounds(i);
                if (prevIndex != -1 && prevBounds.y + prevBounds.height < r.y) {
                    // TODO: optimize this by painting once to an image
                    // and copying
                    Rectangle space = new Rectangle(0, prevBounds.y
                        + prevBounds.height, r.x, r.y - prevBounds.y
                        - prevBounds.height);
                    for (int j = 0; j < space.height; j++) {
                        Graphics g2 = g.create(space.x, space.y + j,
                                               space.width, 1);
                        ((Graphics2D)g2).translate(0, -r2.y - 1);
                        tree.paint(g2);
                    }
                }
                Graphics g2 = g.create(0, r.y, r.x + r.width, r.height);
                ((Graphics2D)g2).translate(0, -r2.y);
                tree.paint(g2);
                prevIndex = i;
                prevBounds = r;
            }
            if (counter != null)
                counter.painted();
        }
        finally {
            tree.setDoubleBuffered(db);
        }
    }

    /**
     * Simple JList-local drag/drop handler. Invokes the smoother
     * according to user input. A similar method could be used to accept
     * drags originating outside of the JList.
     */
    static class Listener extends MouseAdapter implements MouseMotionListener {
        private TreeAnimator smoother;
        private boolean dragActive;
        private Point origin;

        public Listener(TreeAnimator smoother) {
            this.smoother = smoother;
        }

        private boolean sufficientMove(Point where) {
            int dx = Math.abs(origin.x - where.x);
            int dy = Math.abs(origin.y - where.y);
            return Math.sqrt(dx * dx + dy * dy) > 5;
        }

        public void mousePressed(MouseEvent e) {
           // origin = e.getPoint();
        }

        public void mouseReleased(MouseEvent e) {
         //   if (dragActive) {
          //      smoother.endDrag(e.getPoint());
         //       dragActive = false;
         //   }
        }

        public void mouseDragged(MouseEvent e) {
          //  if (!dragActive) {
          //      if (sufficientMove(e.getPoint())) {
           //         dragActive = smoother.startDrag(origin);
           //     }
          //  }
         //   if (dragActive)
          //      smoother.setInsertionLocation(e.getPoint());
        }

        public void mouseExited(MouseEvent e) {
           // if (dragActive)
           //     smoother.setInsertionLocation(e.getPoint());
        }

        public void mouseEntered(MouseEvent e) {
           // if (dragActive)
          //      smoother.setInsertionLocation(e.getPoint());
        }

        public void mouseMoved(MouseEvent e) {
        }
    }

    /**
     * Throw up a frame to demonstrate the smoother at work. Funkify the
     * list renderer to demonstrate that customized renderers are
     * handled properly.
     */
    /*
    public static void main(String[] args) {
        JFrame f = new JFrame("Smooth Tree Drop");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JTree tree = new JTree();
        tree.setFont(tree.getFont().deriveFont(Font.BOLD,
                                               tree.getFont().getSize() * 1.4f));
        TreeAnimator animator = new TreeAnimator(tree);
        Listener listener = new Listener(animator);
        tree.addMouseListener(listener);
        tree.addMouseMotionListener(listener);
        JLabel label = new JLabel("Drag items to reorder");
        label.setBorder(new EmptyBorder(4, 4, 4, 4));
        label.setFont(label.getFont().deriveFont(Font.BOLD,
                                                 label.getFont().getSize() * 2));
        label.putClientProperty("decorator",
                                new AbstractComponentDecorator(label, -1) {
                                    public void paint(Graphics g) {
                                        Rectangle b = getDecorationBounds();
                                        ((Graphics2D)g).setPaint(new GradientPaint(0, b.height / 2,
                                                                                   UIManager.getColor("Tree.selectionBackground"),
                                                                                   b.width / 2,
                                                                                   b.height / 2,
                                                                                   Color.white));
                                        g.fillRect(b.x, b.y, b.width, b.height);
                                    }
                                });
        f.getContentPane().add(label, BorderLayout.NORTH);
        f.getContentPane().add(new JScrollPane(tree));
        f.pack();
        f.setVisible(true);
    }*/
}