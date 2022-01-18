package com.sort.sortcore.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.s3.model.S3Object;
import com.sort.sortcore.api.MainBannerServiceApi;
import com.sort.sortcore.data.EEventGenre;
import com.sort.sortcore.data.EMovieGenre;
import com.sort.sortcore.data.EMovieLanguage;
import com.sort.sortcore.data.EventGenre;
import com.sort.sortcore.data.Favourites;
import com.sort.sortcore.data.LikeDislike;
import com.sort.sortcore.data.LikeDislikeRequest;
import com.sort.sortcore.data.MainBannerContent;
import com.sort.sortcore.data.MovieGenre;
import com.sort.sortcore.data.MovieLanguage;
import com.sort.sortcore.data.PreferenceRequest;
import com.sort.sortcore.data.Profile;
import com.sort.sortcore.data.ProfileRequest;
import com.sort.sortcore.data.Provider;
import com.sort.sortcore.data.SortedData;
import com.sort.sortcore.data.TrueFan;
import com.sort.sortcore.data.TxnContent;
import com.sort.sortcore.data.TxnContentVideo;
import com.sort.sortcore.data.User;
import com.sort.sortcore.repository.EventGenreRepository;
import com.sort.sortcore.repository.FavouritesRepository;
import com.sort.sortcore.repository.LikeDislikeRepository;
import com.sort.sortcore.repository.MovieGenreRepository;
import com.sort.sortcore.repository.MovieLanguageRepository;
import com.sort.sortcore.repository.ProfileRepository;
import com.sort.sortcore.repository.TrueFanRepository;
import com.sort.sortcore.repository.TxnContentVideoRepository;
import com.sort.sortcore.repository.UserRepository;
import com.sort.sortcore.security.jwt.JwtUtils;
import com.sort.sortcore.service.SortDataService;
import com.sort.sortcore.service.impl.DocumentManagementServiceImpl;

import io.swagger.annotations.ApiOperation;

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

    @Autowired
    MovieGenreRepository movieGenreRepository;

    @Autowired
    MovieLanguageRepository movieLanguageRepository;

    @Autowired
    EventGenreRepository eventGenreRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    FavouritesRepository favouritesRepository;

    @Autowired
    LikeDislikeRepository likeDislikeRepository;
    
    @Autowired
    TrueFanRepository trueFanRepository;
    
    @Autowired
    TxnContentVideoRepository txnContentVideoRepository;

    @ApiOperation(value = "Featured Content which are prioritized within active content", notes = "Service for featured content with priority active content.")
    @GetMapping(value = "/featuredBanner", produces = "application/json")
    public CompletableFuture<List<MainBannerContent>> getMainBanner(@RequestParam Integer page) {
        return this.mainBannerServiceApi.getMainBannerData("",page);
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
    public CompletableFuture<List<MainBannerContent>> getMainBannerMovieEventData(@PathVariable String txnType, @RequestParam Integer page) {
        return this.mainBannerServiceApi.getMainBannerMovieEventData(txnType, "", page);
    }

    @GetMapping(value = "/search/{searchType}/{searchText}", produces = "application/json")
    public CompletableFuture<List<MainBannerContent>> searchData(@PathVariable String searchType, @PathVariable String searchText, @RequestParam Integer page) {
        return this.mainBannerServiceApi.getSearchData(searchText, searchType, page);
    }

    @GetMapping({"/recommendedList/{txnType}"})
    public CompletableFuture<List<MainBannerContent>> getRecommendedBannerMovieEventData(@PathVariable String txnType, @RequestParam Integer page) {
        return this.mainBannerServiceApi.getRecommendedBannerMovieEventData(txnType, "", page);
    }

    @GetMapping({"/list/{txnType}"})
    public CompletableFuture<List<MainBannerContent>> getTxnTypeList(@PathVariable String txnType, @RequestParam Integer page) {
        return this.mainBannerServiceApi.getTxnTypeList(txnType, "", page);
    }

    @GetMapping({"/details/{txnType}/{txnId}"})
    public ResponseEntity<List<TxnContent>> getTxnDetails(@PathVariable String txnType, @PathVariable String txnId, @RequestHeader("Authorization") String token) {
        String tempEmail = jwtUtils.getUserEmailFromJwtToken(token.substring(7));
        String str = tempEmail.substring(tempEmail.lastIndexOf("@") + 1);
        String tempProvider = str.replace(".com", "");
        if (!("GOOGLE".equalsIgnoreCase(tempProvider) || "FACEBOOK".equalsIgnoreCase(tempProvider) || "APPLE".equalsIgnoreCase(tempProvider))) {
            tempProvider = "LOCAL";
        }
        User user = userRepository.findByEmailAndProvider(tempEmail, Provider.valueOf(tempProvider.toUpperCase())).get();

        List<Favourites> favouritesList = favouritesRepository.findAllByUserIdAndItemType(user.getId(), txnType);
        List<String> favListString = new ArrayList<>();
        favouritesList.forEach(favList -> {
            favListString.add(favList.getItemId());
        });

        LikeDislike likeDislike;
        if (likeDislikeRepository.existsByUserIdAndItemTypeAndItemId(user.getId(), txnType, txnId)) {
            likeDislike = likeDislikeRepository.findByUserIdAndItemTypeAndItemId(user.getId(), txnType, txnId);
        } else {
            likeDislike = new LikeDislike();
        }

        List<TxnContent> txnContents = mainBannerServiceApi.getTxnDetailsById(txnType, txnId, "");
        TxnContent txncnt = txnContents.get(0);
        txncnt.setTxnSource("https://sortplatformlogos.s3.us-east-2.amazonaws.com/" + txncnt.getTxnSource() + ".png");
        if (favListString.contains(txnId)) {
            txncnt.setAudWishlistFlag("1");
        }
        txncnt.setAudLikeFlag(likeDislike.getLikeFlag());
        txncnt.setAudUnlikeFlag(likeDislike.getDislikeFlag());
        txnContents.set(0, txncnt);
        return new ResponseEntity<>(txnContents, HttpStatus.OK);
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
    public ResponseEntity<Profile> saveProfileData(@RequestBody ProfileRequest profileRequest, @RequestHeader("Authorization") String token) {
        String tempEmail = jwtUtils.getUserEmailFromJwtToken(token.substring(7));
        Profile profile = profileRepository.findByEmailAndProvider(tempEmail, Provider.valueOf(profileRequest.getProvider().toUpperCase()));

        profile.setDisplayEmail(profileRequest.getDisplayEmail());
        profile.setFullName(profileRequest.getFullName());
        profile.setPhone(profileRequest.getPhone());
        profile.setGender(profileRequest.getGender());
        profile.setDateOfBirth(profileRequest.getDateOfBirth());
        profile.setAvatarImage(profileRequest.getAvatarImage());
        profile.setLastModified(LocalDateTime.now());

        profileRepository.save(profile);

        return new ResponseEntity<>(profile, HttpStatus.CREATED);
    }

    @GetMapping({"/getProfileByEmail"})
    public ResponseEntity<Profile> getProfileByEmail(@RequestHeader("Authorization") String token) {
        String tempEmail = jwtUtils.getUserEmailFromJwtToken(token.substring(7));
        String str = tempEmail.substring(tempEmail.lastIndexOf("@") + 1);
        String tempProvider = str.replace(".com", "");
        if (!("GOOGLE".equalsIgnoreCase(tempProvider) || "FACEBOOK".equalsIgnoreCase(tempProvider) || "APPLE".equalsIgnoreCase(tempProvider))) {
            tempProvider = "LOCAL";
        }
        return new ResponseEntity<>(profileRepository.findByEmailAndProvider(tempEmail, Provider.valueOf(tempProvider.toUpperCase())), HttpStatus.OK);
    }

    @GetMapping(value = "/genreList/{itemType}/{genreType}", produces = "application/json")
    public ResponseEntity<?> downloadFile(@PathVariable String itemType, @PathVariable String genreType, @RequestParam Integer page) {
        String json = "[]";
        try {
            /*File resource = new ClassPathResource("/static/json_10-13-2021_12-34-27.json").getFile();
            File resource1 = new File()
            json = new String(Files.readAllBytes(resource.toPath()));*/
            
        	S3Object data = documentManagementService.downloadFileFromS3bucket(itemType + ".json", "myjsonbucket");
            json = documentManagementService.movieObjectForPaging(data,genreType,page);
        } catch (Exception e) {
        	log.error(e.getMessage());
        }
        return ResponseEntity.ok(json);
    }

    @PostMapping(value = "/addPreferences", produces = "application/json", consumes = "application/json")
    public ResponseEntity<PreferenceRequest> addPreferences(@RequestBody PreferenceRequest preferenceRequest) {
        User user = userRepository.findByEmailAndProvider(preferenceRequest.getEmail(), Provider.valueOf(preferenceRequest.getProvider().toUpperCase())).get();

        Set<String> movieGenres = preferenceRequest.getMovieGenres();
        Set<String> movieLanguages = preferenceRequest.getMovieLanguages();
        Set<String> eventGenres = preferenceRequest.getEventGenre();

        Set<MovieGenre> movieGenresSet = new HashSet<>();
        Set<MovieLanguage> movieLanguageSet = new HashSet<>();
        Set<EventGenre> eventGenresSet = new HashSet<>();

        movieGenres.forEach(movieG -> {
            MovieGenre movieGenre = movieGenreRepository.findByMovieGenre(EMovieGenre.valueOf(movieG.toUpperCase())).orElseThrow(() -> new RuntimeException("Error: Movie Genre(s) are not found."));
            movieGenresSet.add(movieGenre);
        });
        movieLanguages.forEach(movieL -> {
            MovieLanguage movieLanguage = movieLanguageRepository.findByMovieLanguage(EMovieLanguage.valueOf(movieL.toUpperCase())).orElseThrow(() -> new RuntimeException("Error: Movie Language(s) are not found."));
            movieLanguageSet.add(movieLanguage);
        });
        eventGenres.forEach(eventG -> {
            EventGenre eventGenre = eventGenreRepository.findByEventGenre(EEventGenre.valueOf(eventG.toUpperCase())).orElseThrow(() -> new RuntimeException("Error: Event Genre(s) are not found."));
            eventGenresSet.add(eventGenre);
        });

        user.setMovieGenres(movieGenresSet);
        user.setMovieLanguage(movieLanguageSet);
        user.setEventGenres(eventGenresSet);

        userRepository.save(user);

        PreferenceRequest preferenceRequest1 = new PreferenceRequest();
        preferenceRequest1.setEmail(preferenceRequest.getEmail());
        preferenceRequest1.setProvider(preferenceRequest.getProvider());
        preferenceRequest1.setMovieGenres(movieGenresSet.stream().map(Object::toString).collect(Collectors.toSet()));
        preferenceRequest1.setMovieLanguages(movieLanguageSet.stream().map(Object::toString).collect(Collectors.toSet()));
        preferenceRequest1.setEventGenre(eventGenresSet.stream().map(Object::toString).collect(Collectors.toSet()));

        return new ResponseEntity<>(preferenceRequest1, HttpStatus.OK);
    }

    @GetMapping(value = "/getPreferences", produces = "application/json")
    public ResponseEntity<PreferenceRequest> getPreferences(@RequestHeader("Authorization") String token) {
        String tempEmail = jwtUtils.getUserEmailFromJwtToken(token.substring(7));
        String str = tempEmail.substring(tempEmail.lastIndexOf("@") + 1);
        String tempProvider = str.replace(".com", "");
        if (!("GOOGLE".equalsIgnoreCase(tempProvider) || "FACEBOOK".equalsIgnoreCase(tempProvider) || "APPLE".equalsIgnoreCase(tempProvider))) {
            tempProvider = "LOCAL";
        }
        User user = userRepository.findByEmailAndProvider(tempEmail, Provider.valueOf(tempProvider.toUpperCase())).get();

        Set<MovieGenre> movieGenresSet = movieGenreRepository.findAllByUserId(user.getId());
        Set<MovieLanguage> movieLanguageSet = movieLanguageRepository.findAllByUserId(user.getId());
        Set<EventGenre> eventGenresSet = eventGenreRepository.findAllByUserId(user.getId());

        PreferenceRequest preferenceRequest1 = new PreferenceRequest();
        preferenceRequest1.setEmail(tempEmail);
        preferenceRequest1.setProvider(tempProvider);
        preferenceRequest1.setMovieGenres(movieGenresSet.stream().map(Object::toString).collect(Collectors.toSet()));
        preferenceRequest1.setMovieLanguages(movieLanguageSet.stream().map(Object::toString).collect(Collectors.toSet()));
        preferenceRequest1.setEventGenre(eventGenresSet.stream().map(Object::toString).collect(Collectors.toSet()));

        return new ResponseEntity<>(preferenceRequest1, HttpStatus.OK);
    }

    @GetMapping(value = "/addFavourites/{itemType}/{itemId}", produces = "application/json")
    public ResponseEntity addFavourites(@PathVariable String itemType, @PathVariable String itemId, @RequestHeader("Authorization") String token) {
        String tempEmail = jwtUtils.getUserEmailFromJwtToken(token.substring(7));
        String str = tempEmail.substring(tempEmail.lastIndexOf("@") + 1);
        String tempProvider = str.replace(".com", "");
        if (!("GOOGLE".equalsIgnoreCase(tempProvider) || "FACEBOOK".equalsIgnoreCase(tempProvider) || "APPLE".equalsIgnoreCase(tempProvider))) {
            tempProvider = "LOCAL";
        }
        User user = userRepository.findByEmailAndProvider(tempEmail, Provider.valueOf(tempProvider.toUpperCase())).get();

        if (favouritesRepository.existsByUserIdAndItemTypeAndItemId(user.getId(), itemType, itemId)) {
            favouritesRepository.delete(favouritesRepository.findByUserIdAndItemTypeAndItemId(user.getId(), itemType, itemId));
            return new ResponseEntity("Item successfully removed from Favourites list", HttpStatus.OK);
        } else {
            Favourites favourites = new Favourites();
            favourites.setUserId(user.getId());
            favourites.setItemType(itemType);
            favourites.setItemId(itemId);
            favouritesRepository.save(favourites);

            return new ResponseEntity("Item successfully added to Favourites list", HttpStatus.CREATED);
        }
    }

    @GetMapping(value = "/getFavourites/{itemType}", produces = "application/json")
    public CompletableFuture<List<MainBannerContent>> getFavourites(@PathVariable String itemType, @RequestHeader("Authorization") String token) {
        String tempEmail = jwtUtils.getUserEmailFromJwtToken(token.substring(7));
        String str = tempEmail.substring(tempEmail.lastIndexOf("@") + 1);
        String tempProvider = str.replace(".com", "");
        if (!("GOOGLE".equalsIgnoreCase(tempProvider) || "FACEBOOK".equalsIgnoreCase(tempProvider) || "APPLE".equalsIgnoreCase(tempProvider))) {
            tempProvider = "LOCAL";
        }
        User user = userRepository.findByEmailAndProvider(tempEmail, Provider.valueOf(tempProvider.toUpperCase())).get();
        List<Favourites> favouritesList = favouritesRepository.findAllByUserIdAndItemType(user.getId(), itemType);
        List<String> favListString = new ArrayList<>();
        favouritesList.forEach(favList -> {
            favListString.add(favList.getItemId());
        });
        StringBuilder sb = new StringBuilder("");
        favListString.forEach(movieID -> {
            sb.append(movieID + " ");
        });
        return mainBannerServiceApi.getTxnDetailsFavouritesById(sb.toString(), "");
    }
    
    @GetMapping(value = "/addTrueFan/{itemType}/{itemId}/{amount}", produces = "application/json")
    public ResponseEntity addTrueFan(@PathVariable String itemType, @PathVariable String itemId, @PathVariable Integer amount, @RequestHeader("Authorization") String token) {
        String tempEmail = jwtUtils.getUserEmailFromJwtToken(token.substring(7));
        String str = tempEmail.substring(tempEmail.lastIndexOf("@") + 1);
        String tempProvider = str.replace(".com", "");
        if (!("GOOGLE".equalsIgnoreCase(tempProvider) || "FACEBOOK".equalsIgnoreCase(tempProvider) || "APPLE".equalsIgnoreCase(tempProvider))) {
            tempProvider = "LOCAL";
        }
        User user = userRepository.findByEmailAndProvider(tempEmail, Provider.valueOf(tempProvider.toUpperCase())).get();

        /*if (trueFanRepository.existsByUserIdAndItemTypeAndItemId(user.getId(), itemType, itemId)) {
            trueFanRepository.delete(trueFanRepository.findByUserIdAndItemTypeAndItemId(user.getId(), itemType, itemId));
            return new ResponseEntity("Item successfully removed from True Fan list", HttpStatus.OK);
        } else {*/
        TrueFan trueFan = new TrueFan();
        trueFan.setUserId(user.getId());
        trueFan.setItemType(itemType);
        trueFan.setItemId(itemId);
        trueFan.setDonatedAmount(amount);
        trueFanRepository.save(trueFan);

        return new ResponseEntity("Item successfully added to True Fan list", HttpStatus.CREATED);
        // }
    }

    @GetMapping(value = "/getTrueFan/{itemType}", produces = "application/json")
    public CompletableFuture<List<MainBannerContent>> getTrueFan(@PathVariable String itemType, @RequestHeader("Authorization") String token) {
        String tempEmail = jwtUtils.getUserEmailFromJwtToken(token.substring(7));
        String str = tempEmail.substring(tempEmail.lastIndexOf("@") + 1);
        String tempProvider = str.replace(".com", "");
        if (!("GOOGLE".equalsIgnoreCase(tempProvider) || "FACEBOOK".equalsIgnoreCase(tempProvider) || "APPLE".equalsIgnoreCase(tempProvider))) {
            tempProvider = "LOCAL";
        }
        User user = userRepository.findByEmailAndProvider(tempEmail, Provider.valueOf(tempProvider.toUpperCase())).get();

        List<TrueFan> trueFanList = trueFanRepository.findAllByUserIdAndItemType(user.getId(), itemType);
        List<String> trueFanListString = new ArrayList<>();
        trueFanList.forEach(tempTrueFanList -> {
            trueFanListString.add(tempTrueFanList.getItemId());
        });
        StringBuilder sb = new StringBuilder("");
        trueFanListString.forEach(movieID -> {
            sb.append(movieID + " ");
        });
        return mainBannerServiceApi.getTxnDetailsFavouritesById(sb.toString(), "");
    }
    
	@GetMapping(value = "/getTxnVideoContent", produces = "application/json")
	public ResponseEntity<List<TxnContentVideo>> getTxnVideoContent() {
		List<TxnContentVideo> contentVideos = (List<TxnContentVideo>) txnContentVideoRepository.findAll();
		return new ResponseEntity<>(contentVideos, HttpStatus.OK);
	}

	@GetMapping(value = "/getTxnVideoContent/{itemId}", produces = "application/json")
	public ResponseEntity<List<TxnContentVideo>> getTxnVideoContentByTxnId(@PathVariable String itemId) {
		List<TxnContentVideo> contentVideos = (List<TxnContentVideo>) txnContentVideoRepository.findByTxnId(itemId);
		return new ResponseEntity<>(contentVideos, HttpStatus.OK);
	}

    @GetMapping(value = "/fetchGenreData/{genreType}", produces = "application/json")
    public List<?> fetchMovieLanguages(@PathVariable String genreType) {
        if ("moviegenre".equalsIgnoreCase(genreType)) {
            return movieGenreRepository.findAllByIdGreaterThan(1L);
        } else if ("movielanguage".equalsIgnoreCase(genreType)) {
            return movieLanguageRepository.findAllByIdGreaterThan(1L);
        } else {
            return eventGenreRepository.findAllByIdGreaterThan(1L);
        }
    }

    @PostMapping(value = "/addChoice", produces = "application/json")
    public ResponseEntity addChoice(@RequestBody LikeDislikeRequest likeDislikeRequest, @RequestHeader("Authorization") String token) {
        String tempEmail = jwtUtils.getUserEmailFromJwtToken(token.substring(7));
        String str = tempEmail.substring(tempEmail.lastIndexOf("@") + 1);
        String tempProvider = str.replace(".com", "");
        if (!("GOOGLE".equalsIgnoreCase(tempProvider) || "FACEBOOK".equalsIgnoreCase(tempProvider) || "APPLE".equalsIgnoreCase(tempProvider))) {
            tempProvider = "LOCAL";
        }
        User user = userRepository.findByEmailAndProvider(tempEmail, Provider.valueOf(tempProvider.toUpperCase())).get();

        LikeDislike likeDislike;
        if (likeDislikeRepository.existsByUserIdAndItemTypeAndItemId(user.getId(), likeDislikeRequest.getItemType(), likeDislikeRequest.getItemId())) {
            likeDislike = likeDislikeRepository.findByUserIdAndItemTypeAndItemId(user.getId(), likeDislikeRequest.getItemType(), likeDislikeRequest.getItemId());
        } else {
            likeDislike = new LikeDislike();
        }
        likeDislike.setUserId(user.getId());
        likeDislike.setItemType(likeDislikeRequest.getItemType());
        likeDislike.setItemId(likeDislikeRequest.getItemId());
        likeDislike.setLikeFlag(likeDislikeRequest.getLikeFlag());
        likeDislike.setDislikeFlag(likeDislikeRequest.getDislikeFlag());
        likeDislikeRepository.save(likeDislike);

        return new ResponseEntity("Like and Dislike flags have been updated successfully", HttpStatus.CREATED);
    }
}