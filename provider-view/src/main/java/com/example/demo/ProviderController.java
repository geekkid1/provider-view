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

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
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
        // get datetime formatter
        DateTimeFormatter dtFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                .withZone(ZoneId.systemDefault());
        FeedbackData[] all;
        if(product.equalsIgnoreCase("all")) {
            all = dbs.getAll();
        } else {
            all = dbs.getFbByProduct(product);
        }
        DummyFilterClass dfc = dbs.getFilter(product);
        ArrayList<Long> updated = (ArrayList<Long>) urs.getUpdated();
        ArrayList<HashMap<String,Object>> data = new ArrayList<>();
        for (FeedbackData fd : all) {
            HashMap<String,Object> datum = new HashMap<>();
            datum.put("id",fd.id+"");
            datum.put("content",fd.content);
            Instant created = null;
            try {
                created = Instant.parse(fd.metaData.get("DateTime"));
                datum.put("datetime", dtFormat.format(created));
            } catch (DateTimeParseException ignored) {
                datum.put("datetime", "");
            }
            if(fd.metaData.get("isBugReport") == null) {
                datum.put("bugreport", "");
            } else if(fd.metaData.get("isBugReport").equalsIgnoreCase("true")) {
                datum.put("bugreport", "Y");
            } else {
                datum.put("bugreport", "N");
            }
            datum.put("updated",updated.contains(fd.id));
            if((created == null && dfc.getLen() == 'A')||(created != null && dbs.filter(created, dfc.getLen()))) {
                if(dfc.bp == 'A'|| (dfc.bp == 'N' && (datum.get("bugreport").equals("N") || datum.get("bugreport").equals(""))) || (dfc.bp == 'Y' && datum.get("bugreport").equals("Y")))
                    data.add(datum);
            }
        }
        List<String> headers = Arrays.asList(new String[]{"id","content","datetime","bugreport"});
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

    @PostMapping("/filterbp")

    @GetMapping("/all")
    public ModelAndView all(Model model) {
        return new ModelAndView("redirect:table/all");
    }
}
