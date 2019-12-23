import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Map;

class ModelApi
{
    private StringBuffer response = new StringBuffer();
    int responseCode;       //код соединения

    ModelApi(String url) throws MalformedURLException
    {
        URL obj = new URL(url);     //присваиваем полученный при вызове конструктора url
        try
        {
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            responseCode = connection.getResponseCode();        //код ответа соединения
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                  response.append(inputLine);     //получаем апи
        in.close();
        }
        catch (Exception e)
        {
            //можно логировтаь ошибки подключения, но мы обрабатываем код подключения
        }
    }

    String vBTC(String vallet)       //функция преобразования апи в текущую валюту
    {
        try
        {
            JSONObject zapros = new JSONObject(new JSONObject(response.toString()).get("bpi").toString());//получаем данные по валюте из запрошенного апм
            return new JSONObject(zapros.get(vallet).toString()).get("rate").toString();        //результат функции (строка)
        }
        catch (Exception e)
        {
            return e.toString();        //проверка на правильность написания валюты (кидаем исключение ответом на функцию (Строка))
        }
    }

    double[] historyBTC(String vallet) throws JSONException      //функция преобразования апи в min и max (ответ в массиве)
    {
        JSONObject zapros = new JSONObject(new JSONObject(response.toString()).get("bpi").toString());      //функция получения истории
        Map<String, Double> map = new Gson().fromJson(zapros.toString(),Map.class);     //конвертируем таблицу json в Map
        double max = Collections.max(map.values());     //извлекаем максимальное значение
        double min = Collections.min(map.values());     //извлекаем минимальное значение
        return new double[] {min,max};      //создаем ответ на функцию массивом, чтобы извлечь оба результата
    }
}
