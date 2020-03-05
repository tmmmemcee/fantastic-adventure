/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.sighting;

import com.tm.sighting.dao.PictureDao;
import com.tm.sighting.dao.SuperHeroDao;
import com.tm.sighting.model.Picture;
import com.tm.sighting.model.SuperHero;
import java.io.File;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author tmmmemcee
 */
@Controller
public class PictureController {
    public static final String pictureFolder = "images/";
    private PictureDao dao;
    private SuperHeroDao superDao;
    
    @Inject
    public PictureController(PictureDao dao, SuperHeroDao superDao) {
        this.dao = dao;
        this.superDao = superDao;
    }
    @RequestMapping(value="addPictureForm", method=RequestMethod.GET)
    public String displayAddPictureForm(Model model) {
        List<SuperHero> heros = superDao.getAllSuperHeros();
        model.addAttribute("superList", heros);
        return "addPictureForm";
    }
    @RequestMapping(value="/addPicture", method=RequestMethod.POST)
    public String addPicture(HttpServletRequest request,
                    Model model,
                    
                    @RequestParam("picture") MultipartFile pictureFile) {

        // only save the pictureFile if the user actually uploaded something
        if (!pictureFile.isEmpty()) {
            try {
                // we want to put the uploaded image into the 
                // <pictureFolder> folder of our application. getRealPath
                // returns the full path to the directory under Tomcat
                // where we can save files.
                String savePath = request
                        .getSession()
                        .getServletContext()
                        .getRealPath("/") + pictureFolder;
                File dir = new File(savePath);
                // if <pictureFolder> directory is not there, 
                // go ahead and create it
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // get the filename of the uploaded file - we'll use the
                // same name on the server.
                String filename = pictureFile.getOriginalFilename();
                // transfer the contents of the uploaded pictureFile to 
                // the server
                pictureFile.transferTo(new File(savePath + filename));

                // we successfully saved the pictureFile, now save a 
                // Picture to the DAO
                Picture picture = new Picture();
                picture.setFilename(pictureFolder + filename);
                picture.setTitle("Title");
//                dao.addPicture(picture);

                // redirect to home page to redisplay the entire album
                return "redirect:home";
            } catch (Exception e) {
                // if we encounter an exception, add the error message 
                // to the model and return back to the pictureFile upload 
                // form page
                model.addAttribute("errorMsg", "File upload failed: " + 
                        e.getMessage());
                return "addPictureForm";
            }
        } else {
            // if the user didn't upload anything, add the error 
            // message to the model and return back to the pictureFile 
            // upload form page
            model.addAttribute("errorMsg", 
                               "Please specify a non-empty file.");
            return "addPictureForm";
        }


    }
}
