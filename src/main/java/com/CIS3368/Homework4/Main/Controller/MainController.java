package com.CIS3368.Homework4.Main.Controller;
import com.CIS3368.Homework4.Main.Models.ChuckJokesClass;
import com.CIS3368.Homework4.Main.Models.ChuckJokesClassRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Optional;
import java.util.UUID;


@Controller
public class MainController {

    @Autowired
    ChuckJokesClassRepo chuckjokesRepo;

    //call the API whenever the webpage is loaded
    @RequestMapping("/")
    public ModelAndView home(){
        ModelAndView homepage = new ModelAndView("index");
        String joke = "";
        String address = "";
        String combine = "";
        String apikey = "66a1bb8114msh70fe2e1f884c8edp1145a4jsnd34b35b1adb2";
        try
        {
            URL url = new URL("https://matchilling-chuck-norris-jokes-v1.p.rapidapi.com/jokes/random");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-rapidapi-host", "matchilling-chuck-norris-jokes-v1.p.rapidapi.com");
            connection.setRequestProperty("x-rapidapi-key", apikey);

            connection.connect();
            BufferedReader reader  = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            JSONObject object = new JSONObject(response.toString());
            joke = object.getString("value");
            address = object.getString("url");
            combine = joke + ". THIS SOURCE IS FROM: " + address;
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }

        //print out the json response into the textarea as well as the text field
        homepage.addObject("listitems", chuckjokesRepo.findAll());
        homepage.addObject("chuckjoke", combine);
        return homepage;
    }


    //save the Json response into the database
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@RequestParam("id") String id, @RequestParam("joke") String result){
        ModelAndView savepage = new ModelAndView("redirect:/");
        ChuckJokesClass jokesTosave ;
        if(!id.isEmpty())
        {
            Optional<ChuckJokesClass> users = chuckjokesRepo.findById(id);
            jokesTosave = users.get();
        }
        else
        {
            jokesTosave = new ChuckJokesClass();
            jokesTosave.setId(UUID.randomUUID().toString());
        }
        jokesTosave.setResult(result);
        chuckjokesRepo.save(jokesTosave);
        savepage.addObject("listitems", chuckjokesRepo.findAll());
        return savepage;
    }


    //delete by data from the table
    @RequestMapping( value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") String id){
        ModelAndView home = new ModelAndView("redirect:/");
        chuckjokesRepo.deleteById(id);
        return home;
    }
}

