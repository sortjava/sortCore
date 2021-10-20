package com.sort.sortcore.controller;

import com.amazonaws.services.s3.model.S3Object;
import com.sort.sortcore.api.MainBannerServiceApi;
import com.sort.sortcore.data.*;
import com.sort.sortcore.repository.ProfileRepository;
import com.sort.sortcore.repository.UserRepository;
import com.sort.sortcore.service.SortDataService;
import com.sort.sortcore.service.impl.DocumentManagementServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/sort")
public class MainBannerController {
    private static final Logger log = LoggerFactory.getLogger(MainBannerController.class);
    @Autowired
    MainBannerServiceApi mainBannerServiceApi;

    @Autowired
    SortDataService sortDataService;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    DocumentManagementServiceImpl documentManagementService;

    @Autowired
    UserRepository userRepository;

    @ApiOperation(value = "Featured Content which are prioritized within active content", notes = "Service for featured content with priority active content.")
    @GetMapping(value = "/featuredBanner", produces = "application/json")
    public CompletableFuture<List<MainBannerContent>> getMainBanner() {
        return this.mainBannerServiceApi.getMainBannerData();
    }
    /*public CompletableFuture<List<MainBannerContent>> getMainBanner() throws IOException {
		CompletableFuture<List<MainBannerContent>> listCompletableFuture = new CompletableFuture();
		try {
			listCompletableFuture = this.mainBannerServiceApi.getMainBannerData();
		} catch (ElasticsearchException e){
			e.getMessage();
		} catch (Exception e){
			e.getMessage();
		}
		return listCompletableFuture;
    }*/
    
    @GetMapping(value = "/mainBanner/{txnType}", produces = "application/json")
    public CompletableFuture<List<MainBannerContent>> getMainBannerMovieEventData(@PathVariable String txnType) {
        return this.mainBannerServiceApi.getMainBannerMovieEventData(txnType);
    }

    @GetMapping({"/recommendedList/{txnType}"})
    public CompletableFuture<List<MainBannerContent>> getRecommendedBannerMovieEventData(@PathVariable String txnType) {
        return this.mainBannerServiceApi.getRecommendedBannerMovieEventData(txnType);
    }

    @GetMapping({"/list/{txnType}"})
    public CompletableFuture<List<MainBannerContent>> getTxnTypeList(@PathVariable String txnType) {
        return this.mainBannerServiceApi.getTxnTypeList(txnType);
    }

    @GetMapping({"/details/{txnType}/{txnId}"})
    public ResponseEntity<List<TxnContent>> getTxnDetails(@PathVariable String txnType, @PathVariable String txnId) {
        return new ResponseEntity<>(mainBannerServiceApi.getTxnDetailsById(txnType, txnId), HttpStatus.OK);
    }

    @GetMapping({"/getAllSortedData"})
    public ResponseEntity<List<SortedData>> getAllSortedData() {
        List<SortedData> sortedData = sortDataService.getSortedData();
        return new ResponseEntity<>(sortedData, HttpStatus.OK);
    }

    @GetMapping({"/getSortedDataById/{sortedDataId}"})
    public ResponseEntity<SortedData> getSotedDataById(@PathVariable Long sortedDataId) {
        return new ResponseEntity<>(sortDataService.getSortedDataById(sortedDataId), HttpStatus.OK);
    }

    @PostMapping(value = "/addSortedData", produces = "application/json", consumes = "application/json")
    public ResponseEntity<SortedData> saveSortedData(@RequestBody SortedData sortedData) {
        SortedData sortedData2 = sortDataService.insert(sortedData);
        return new ResponseEntity<>(sortedData2, HttpStatus.CREATED);
    }

    @PostMapping(value = "/addListSortedData", produces = "application/json", consumes = "application/json")
    public ResponseEntity<SortedData> saveListSortedData(@RequestBody List<SortedData> sortedData) {
        SortedData sortedData1 = new SortedData();
        sortedData1 = sortedData.get(0);
        sortedData1 = sortDataService.insert(sortedData1);
        return new ResponseEntity<>(sortedData1, HttpStatus.CREATED);
    }

    @PutMapping({"/updateSortedDataById/{sortedDataId}"})
    public ResponseEntity<SortedData> updateTodo(@PathVariable("sortedDataId") Long sortedDataId, @RequestBody SortedData sortedData) {
        sortDataService.updateSortedData(sortedDataId, sortedData);
        return new ResponseEntity<>(sortDataService.getSortedDataById(sortedDataId), HttpStatus.OK);
    }

    @DeleteMapping({"/deleteSortedDataById/{sortedDataId}"})
    public ResponseEntity<SortedData> deleteSortedData(@PathVariable("sortedDataId") Long sortedDataId) {
        sortDataService.deleteSortedData(sortedDataId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/updateProfile", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Profile> saveProfileData(@RequestBody ProfileRequest profileRequest) {
        Profile profile = profileRepository.findByEmailAndProvider(profileRequest.getEmail(), Provider.valueOf(profileRequest.getProvider().toUpperCase()));

        profile.setFullName(profileRequest.getFullName());
        profile.setPhone(profileRequest.getPhone());
        profile.setGender(profileRequest.getGender());
        profile.setDateOfBirth(profileRequest.getDateOfBirth());
        profile.setLastModified(LocalDateTime.now());
        profileRepository.save(profile);

        return new ResponseEntity<>(profile, HttpStatus.CREATED);
    }

    @GetMapping({"/getProfileByEmail/{emailId}/{provider}"})
    public ResponseEntity<Profile> getProfileByEmail(@PathVariable String emailId, @PathVariable String provider) {
        return new ResponseEntity<>(profileRepository.findByEmailAndProvider(emailId, Provider.valueOf(provider.toUpperCase())), HttpStatus.OK);
    }

    @GetMapping(value = "/genreList/{itemType}", produces = "application/json")
    public ResponseEntity<?> downloadFile(@PathVariable String itemType) {
        String json = "";
        try {
            /*File resource = new ClassPathResource("/static/json_10-13-2021_12-34-27.json").getFile();
            File resource1 = new File()
            json = new String(Files.readAllBytes(resource.toPath()));*/

            S3Object data = documentManagementService.downloadFileFromS3bucket(itemType + ".json", "myjsonbucket");
            json = new String(data.getObjectContent().readAllBytes());
        } catch (Exception e) {
            e.getMessage();
        }
        return ResponseEntity.ok(json);
    }

    /*@GetMapping(value = "/addPreferences", produces = "application/json")
    public ResponseEntity<?> addPreferences(@RequestBody PreferenceRequest preferenceRequest) {
        User user = userRepository.findByEmailAndProvider(preferenceRequest.getEmail(), Provider.valueOf(preferenceRequest.getProvider().toUpperCase())).get();

        Set<String> movieGenres = preferenceRequest.getMovieGenres();
        Set<String> movieLanguages = preferenceRequest.getMovieLanguages();
        Set<String> eventGenres = preferenceRequest.getEventGenre();

        Set<MovieGenre> movieGenresSet = new HashSet<>();
        Set<MovieLanguage> movieLanguageSet = new HashSet<>();
        Set<EventGenre> eventGenresSet = new HashSet<>();

        movieGenres.forEach(movieG -> {
            //   MovieGenre movieGenre = mo.findByName(EMovieGenre.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            // movieGenresSet.add(userRole);
        });

        return ResponseEntity.ok(json);
    }*/
}