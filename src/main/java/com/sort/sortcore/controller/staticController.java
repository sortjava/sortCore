package com.sort.sortcore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/static")
public class staticController {
    private static final Logger log = LoggerFactory.getLogger(staticController.class);

    @GetMapping({"/home"})
    public String getHealth() {
        return "Your Entertainment, SIMPLIFIED !";
    }

    @GetMapping(value = "/privacyPolicy", produces = "text/html")
    public ModelAndView privacyPolicy(Model model) {
        return new ModelAndView("PrivacyPolicy");
    }

    @GetMapping(value = "/faqHtml")
    public ModelAndView getFaq(Model model) {
        return new ModelAndView("faq");
    }

    @GetMapping(value = "/faq", produces = "application/json")
    public String fetchJson() {
        String json = "[\n" +
                "   {\n" +
                "      \"question\":\"What is SORT?\",\n" +
                "      \"answer\":\"Sort is a free to download entertainment aggregator. It assists you to search and discover movies, shows and events you might like and are legally available with the providers. SORT also recommends you what to watch or which event to go to based on your preferences.\\nSORT also has a proprietary music identification service - SORTED! which identifies any song playing near you with just a Tap!\\nSORT is world's first 360° entertainment aggregator, Made in India!\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"question\":\"Is SORT available in all countries?\",\n" +
                "      \"answer\":\"Not yet, but we are working on expanding to other territories and will be available soon.\\nSORT is currently available in India only.\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"question\":\"Is SORT free?\",\n" +
                "      \"answer\":\"Absolutely! SORT is available for free for you to download and use all its features including SORTED!\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"question\":\"How is SORT collecting data of movies, shows and events and their availability?\",\n" +
                "      \"answer\":\"We use open source database for information about a title such as posters, casts, directors, synopsis etc. We then use this database to match the titles or events available at different platforms which we update regularly.\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"question\":\"I could not find a title or event when I searched on SORT. Why?\",\n" +
                "      \"answer\":\"It may be because of two reasons:\\n1. That particular title may not be available with any of the providers.\\n2. Or our team has not have it updated in our database. If this is the case, please be assured we are working round the clock to rectify it.\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"question\":\"Is content different in each country?\",\n" +
                "      \"answer\":\"Yes, most services has different content in different country. For example: A movie available on Netflix in India might be available on Hulu in US. We curate our content based on each country.\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"question\":\"Do you know upcoming movies, shows or events which are yet to be listed?\",\n" +
                "      \"answer\":\"We will have this information only when a provider shares this with us either through advertisement or through SORT's partnership with the provider/s. Rest assured, we will share as much information as possible with you which we legally can.\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"question\":\"I was redirected to a different page after click on the content or event provider icon. I am confused as why this has happened?\",\n" +
                "      \"answer\":\"If this has happened, we are very sorry as this is our fault. We probably mixed up the titles in our database. We are working around the clock to make sure we make your experience as perfect as possible.\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"question\":\"How can I list my streaming service or event service with SORT?\",\n" +
                "      \"answer\":\"We are continuously working on adding new service providers to our service. Please get in touch with us at privacy@sort.live.\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"question\":\"I have a great idea and suggestion on how you can make SORT better. How can I get in touch with you?\",\n" +
                "      \"answer\":\"We have been searching for you for your valuable feedback. Write to us info@sort.live with the email title - 'Aishwary, this is how you can make the app better!'\"\n" +
                "   }\n" +
                "]";
        return json;
    }

    @GetMapping(value = "/profileAvatar")
    public List<String> getProfileAvatarImages() {
        List<String> profAvtr = new ArrayList<>();
        profAvtr.add("https://sortprofileavatar.s3.us-east-2.amazonaws.com/male.png");
        profAvtr.add("https://sortprofileavatar.s3.us-east-2.amazonaws.com/female.png");
        profAvtr.add("https://sortprofileavatar.s3.us-east-2.amazonaws.com/superhero1.png");
        profAvtr.add("https://sortprofileavatar.s3.us-east-2.amazonaws.com/superhero2.png");
        profAvtr.add("https://sortprofileavatar.s3.us-east-2.amazonaws.com/superhero3.png");
        profAvtr.add("https://sortprofileavatar.s3.us-east-2.amazonaws.com/superhero4.png");
        profAvtr.add("https://sortprofileavatar.s3.us-east-2.amazonaws.com/superhero5.png");
        profAvtr.add("https://sortprofileavatar.s3.us-east-2.amazonaws.com/superhero6.png");
        profAvtr.add("https://sortprofileavatar.s3.us-east-2.amazonaws.com/superhero7.png");
        profAvtr.add("https://sortprofileavatar.s3.us-east-2.amazonaws.com/superhero8.png");
        profAvtr.add("https://sortprofileavatar.s3.us-east-2.amazonaws.com/lgbt.png");
        return profAvtr;
    }
}