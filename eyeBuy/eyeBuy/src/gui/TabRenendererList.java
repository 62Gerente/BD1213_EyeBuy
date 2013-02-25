package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class TabRenendererList extends JFrame {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JList list;

  public TabRenendererList() {
    super("Swing List with Tab Renenderer");
    setSize(500, 240);

    String[] items = { "cloumn 1\t column  2\t Column 3",
        "cloumn 1\t column  2\t Column 3" };

    list = new JList(items);

    TabListCellRenderer renderer = new TabListCellRenderer(100);
    list.setCellRenderer(renderer);

    JScrollPane scrollPane = new JScrollPane();
    scrollPane.getViewport().add(list);
    getContentPane().add(scrollPane, BorderLayout.CENTER);

    WindowListener exitEvent = new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    };
    addWindowListener(exitEvent);

    setVisible(true);
  }

  public static void main(String argv[]) {
    new TabRenendererList();
  }
}

class TabListCellRenderer extends JLabel implements ListCellRenderer {
  
	private static final long serialVersionUID = 1L;

	private int nr = 0;
	
	protected static Border m_noFocusBorder = new EmptyBorder(1, 1, 1, 1);
  
	protected FontMetrics m_fm = null;
  
  public TabListCellRenderer(int espacamento) {
    super();
    
    nr = espacamento;
    setOpaque(true);
    setBorder(m_noFocusBorder);
  }

  public Component getListCellRendererComponent(JList list, Object value,
      int index, boolean isSelected, boolean cellHasFocus) {
    setText(value.toString());

    setBackground(isSelected ? list.getSelectionBackground() : list
        .getBackground());
    setForeground(isSelected ? list.getSelectionForeground() : list
        .getForeground());

    setFont(list.getFont());
    setBorder((cellHasFocus) ? UIManager
        .getBorder("List.focusCellHighlightBorder") : m_noFocusBorder);

    return this;
  }

  public void paint(Graphics g) {
    m_fm = g.getFontMetrics();

    g.setColor(getBackground());
    g.fillRect(0, 0, getWidth(), getHeight());
    getBorder().paintBorder(this, g, 0, 0, getWidth(), getHeight());

    g.setColor(getForeground());
    g.setFont(getFont());
    Insets insets = getInsets();
    int x = insets.left;
    int y = insets.top + m_fm.getAscent();

    StringTokenizer st = new StringTokenizer(getText(), "\t");
    while (st.hasMoreTokens()) {
      String str = st.nextToken();
      g.drawString(str, x, y);
      //insert distance for each tab
      x += nr;

      if (!st.hasMoreTokens())
        break;
    }
  }
}