import org.json.JSONException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

class Controller
{

    private Frame v;

    Controller(Frame v)
    {
        this.v = v;
        this.v.addAction(new Action());
    }


    class Action implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                ModelApi m1 = new ModelApi(Main.urlcur);
                if (m1.responseCode == 200)
                    if (m1.vBTC(v.getCodText()).contains("JSON"))       //если заданный код курса не поддерживается, т.е проброшенная ошибка содержит "JSON"
                         v.noSupport();                        //прорисовка элементов окна при состоянии "не поддерживает"
                    else                                       //если заданный код курса поддерживается
                    {
                        ModelApi m2 = new ModelApi(Main.urlhis+ "?currency=" +v.getCodText());      //делаем правильной запрос апи
                        double[] res = m2.historyBTC(v.getCodText());       //для наглядности получения ответа
                        v.support(m1.vBTC(v.getCodText()),res[0],res[1]);    //прорисовка элементов окна при состоянии "поддерживает"
                    }
                else        //если код соединения НЕ равен 200 (не успешный), значит соединение не состоялось
                   v.noConnect(m1.responseCode);        //прорисовка элементов окна при состоянии "нет соединения"
            }
            catch (JSONException | MalformedURLException e1)
            {
                e1.printStackTrace();
            }
        }
    }
}