package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Controller
public class ProviderController {
    @Autowired UpdateReceiverService urs;
    @Autowired DatabaseService dbs;

    @GetMapping("/table/{product}")
    public String table(Model model, @PathVariable String product) {
        FeedbackData[] all = dbs.getFbByProduct(product);
        ArrayList<Long> updated = (ArrayList<Long>) urs.getUpdated();
        ArrayList<HashMap<String,Object>> data = new ArrayList<>();
        for (FeedbackData fd : all) {
            if (fd.productName.equals(product)) { // just a double check
                HashMap<String,Object> datum = new HashMap<>();
                datum.put("id",fd.id+"");
                datum.put("content",fd.content);
                datum.put("updated",updated.contains(fd.id));
                data.add(datum);
            }
        }
        List<String> headers = Arrays.asList(new String[]{"id","content"});
        model.addAttribute("headers",headers);
        model.addAttribute("pname", product);
        model.addAttribute("rows",data);
        return "table";
    }
}
