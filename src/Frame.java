import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class Frame extends JFrame
{
    private JLabel curCode = new JLabel("Input a currency Code (USD, EUR, GBP, etc.)");
    private JTextField codText = new JTextField("", 15);
    private JButton gross = new JButton("Conversion");
    private JLabel curBtc = new JLabel("The current Bitcoin rate:");
    private JLabel lowPrice = new JLabel("The lowest Bitcoin rate in the last 30 days : ");
    private JLabel hiPrice = new JLabel("The highest Bitcoin rate in the last 30 days : ");
    private JLabel curText = new JLabel();
    private JLabel lowText = new JLabel();
    private JLabel hiText = new JLabel();

    // Создание окна формы с расположеными элементами на ней
    Frame() throws HeadlessException
    {
        initFrame();        //добавляем в конструктор функцию инициализации окна
    }

    private void initFrame()
    {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);        //чтобы программа автоматически закрывалась, после нажатия выхода из окна
        setTitle("ITSnab");       //заголовок
        setResizable(false);        //не изменять окошко
        setVisible(true);       //чтобы окно было видно
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(10, 1,2,2));       //рабочее окно
        container.add(curCode);
        container.add(codText);
        container.add(gross);
        container.add(curBtc);
        curBtc.setVisible(true);
        container.add(curText);
        curText.setVisible(false);
        container.add(lowPrice);
        container.add(lowText);
        lowText.setVisible(false);
        container.add(hiPrice);
        container.add(hiText);
        hiText.setVisible(false);
        curBtc.setVisible(false);
        lowPrice.setVisible(false);
        hiPrice.setVisible(false);
        setIconImage(new ImageIcon(getClass().getResource("Bitcoin.png")).getImage());  //иконка

        pack();     //изменит размер формы, установит минимальный размер для отображений всех компонентов
        setLocationRelativeTo(null);        //установить окошко по центру
    }

    String getCodText()      //геттер стринговый
    {
        return codText.getText().toUpperCase();
    }

    void noSupport()
    {
        curText.setText("Currency cod : \"" + getCodText() + "\" not supported ");
        curText.setVisible(true);
        curText.setForeground(Color.RED);
        lowText.setVisible(false);
        hiText.setVisible(false);
        curBtc.setVisible(false);
        lowPrice.setVisible(false);
        hiPrice.setVisible(false);
    }

    void support(String current, Double min, Double max)
    {
        curText.setText(current+"  "+getCodText());
        curBtc.setVisible(true);
        lowPrice.setVisible(true);  lowText.setText(min.toString());
        hiPrice.setVisible(true);   hiText.setText(max.toString());
        curText.setVisible(true);   curText.setForeground(Color.MAGENTA);
        lowText.setVisible(true);   lowText.setForeground(Color.BLUE);
        hiText.setVisible(true);    hiText.setForeground(Color.RED);
    }

    void noConnect(int rCode)     //нет соединения, выводим сообщение с кодом соединения
    {
        curText.setText("Connection Error, Code "+rCode);
        curText.setVisible(true);   curText.setForeground(Color.RED);
        lowText.setVisible(false);
        hiText.setVisible(false);
        curBtc.setVisible(false);
        lowPrice.setVisible(false);
        hiPrice.setVisible(false);
    }

    void addAction(ActionListener listenForButton)
    {
        gross.addActionListener(listenForButton);
    }
}
