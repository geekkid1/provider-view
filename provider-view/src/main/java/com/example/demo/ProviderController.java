package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

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
        DummyFilterClass dfc = dbs.getFilter(product);
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
        model.addAttribute("filter", dfc);
        return "table";
    }

    @PostMapping("/filter")
    public ModelAndView filter(@ModelAttribute DummyFilterClass filter, Model model) {
        model.addAttribute("path", "table/" + filter.product);
        dbs.updateFilter(filter);
        //return table(model, filter.product);
        return new ModelAndView("redirect:table/"+filter.product);
    }
}
