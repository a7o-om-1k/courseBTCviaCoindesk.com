import java.net.MalformedURLException;

public class Main
{
    static String urlcur = "https://api.coindesk.com/v1/bpi/currentprice.json";        // url текущего курса
    static String urlhis = "https://api.coindesk.com/v1/bpi/historical/close.json";        //url истории курса + добавляем код валюты далее

    public static void main(String[] args) throws MalformedURLException
    {
        Frame view = new Frame();
        new Controller(view);
        view.setVisible(true);

    }
}
