package com.example.team5ad.service.impl;

import com.example.team5ad.entity.PropertyListing;
import com.example.team5ad.repo.PropertyListingRepository;
import com.example.team5ad.service.ImageService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.List;

/*Used by Ee Loon*/

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    PropertyListingRepository propertyRepo;


    //NEW
    //Using multipart file
    public void saveFileToDirectoryAndListing(MultipartFile[] files, long id) throws IOException {

        PropertyListing propertyListing = propertyRepo.getById(id);
        List<String> imageFileNames = propertyListing.getImageFileNames();

        String uploadDir = "prop-list-photos/" + propertyListing.getId();

        for(MultipartFile file : files) {
            String fileName = UUID.randomUUID().toString();

            //add filename string to property listing List<String> imageFileNames
            imageFileNames.add(fileName);
            propertyRepo.save(propertyListing);

            //upload file to directory
            Path uploadPath = Paths.get(uploadDir);
            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
            try(InputStream inputStream = file.getInputStream()){
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e){
                throw new IOException("Could not save image file: " + file.getOriginalFilename(), e);
            }
        }
    }

    public void editListingWithNewImages(MultipartFile[] files, PropertyListing propertyListing) throws IOException {
        List<String> imageFileNames = propertyListing.getImageFileNames();
        String uploadDir = "prop-list-photos/" + propertyListing.getId();

        for(MultipartFile file : files) {
            if(!file.getOriginalFilename().equals("")) {
                String fileName = UUID.randomUUID().toString();

                //add filename string to property listing List<String> imageFileNames
                imageFileNames.add(fileName);

                //upload file to directory
                Path uploadPath = Paths.get(uploadDir);
                if(!Files.exists(uploadPath)){
                    Files.createDirectories(uploadPath);
                }
                try(InputStream inputStream = file.getInputStream()){
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e){
                    throw new IOException("Could not save image file: " + file.getOriginalFilename(), e);
                }
            }
        }
        propertyRepo.save(propertyListing);

    }

    //NEW
    public void deleteFileFromDirectoryAndListing(long propId, String filename){

        //remove filename from proplist List<String> filename
        PropertyListing propertyListing = propertyRepo.getById(propId);
        List<String> proplistfilenames = propertyListing.getImageFileNames();
        proplistfilenames.remove(filename);

        //delete file from file directory
        String filePathToDelete = "prop-list-photos/" + propId + "/" + filename;

        File delFile = new File(filePathToDelete);
        if(delFile.isFile() && delFile.exists()){
            delFile.delete();
        }
        //Save the property listing back after removing that image file in the list
        propertyRepo.save(propertyListing);
    }

    public void deleteAllFilesOfPropListFromDirectory(long propId){
        String pathToDelete = "prop-list-photos/" + propId;
        File directory = new File(pathToDelete);
        try {
            FileUtils.deleteDirectory(directory);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
