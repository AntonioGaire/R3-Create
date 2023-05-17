package es.gaire.r3create;

import com.thedeanda.lorem.LoremIpsum;
import es.gaire.r3create.domain.*;
import es.gaire.r3create.repository.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class R3CreateApplicationTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccessLevelRepository accessLevelRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private TypeRepository postTypeRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private CommentRepository commentRepository;

    private LoremIpsum lorem = LoremIpsum.getInstance();



    @Test
    @Transactional
    @Rollback(value = false)
    void UserLoads() {

        String[] fpfp = {
                "https://drive.google.com/uc?id=1AQDwaFo4PDtlDWUb2PUQTOCjI2X7HUr2",
                "https://drive.google.com/uc?id=15M62qtk8esoW9wvukU2g4ZqRMarRoenn",
                "https://drive.google.com/uc?id=1Qt1F7muFaYp2UoVgmHFsYrpZgJ2OgsIK",
                "https://drive.google.com/uc?id=1FvhbEx9Qi0-fpbapk0k_8VLQJulQYWm6",
                "https://drive.google.com/uc?id=1j-HgzPeVobz4FeNPE6M9fTBMxlCm1iGN",
                "https://drive.google.com/uc?id=1WyXe7m7YIuZjrsbj5bNxi_QXmvns4r80",
                "https://drive.google.com/uc?id=11AK1FZd6lJDBqKP8T2Lyb-3pczw1Lru9",
                "https://drive.google.com/uc?id=1rlJkEkHzwxIJYqBuKj-uW9VhnFC5EInL",
                "https://drive.google.com/uc?id=1moVlIkE-YBHdAELs_3oFSLqkFL8s_A6j",
                "https://drive.google.com/uc?id=1AelIn2lZsj6MkeW2I_03HaD0JBmIcfc0",
                "https://drive.google.com/uc?id=1mJP8xGmZ77t4SVped5REWy-YdoZ2WxHn",
                "https://drive.google.com/uc?id=1PjwTZscv2xSXwnGbsR5yyKRJAlbRinCV",
                "https://drive.google.com/uc?id=13G2CoOr1OPPlr7E_h9QLAiGM4KG1PPT8",
        };

        String[] mpfp = {
                "https://drive.google.com/uc?id=13XjjUTlrlgcYY7yyV8amfmtnqOkv08FV",
                "https://drive.google.com/uc?id=1twDPH9OJ1Q-ua6vq2L3xyS6B8MXc1rnq",
                "https://drive.google.com/uc?id=1Mul7oD2df8GXhn1I8XyRyEL8hvJPVtu8",
                "https://drive.google.com/uc?id=1s28cPlRZxpociXKAhJlHKSvfyxMY7Hhf",
                "https://drive.google.com/uc?id=1BgAyXZmP1R34t_AR8dmj32SaUWPDBQhD",
                "https://drive.google.com/uc?id=1XgkmxBaoAY_drWqhoFGc3dnclXy--VDx",
                "https://drive.google.com/uc?id=1iSkiYq5aZHR6uuPKP2C76VpEOQ7rkekT",
                "https://drive.google.com/uc?id=1mUR68LC92x8J1qsfaq4LWlUHcvUqygfs",
                "https://drive.google.com/uc?id=1suF9ybp0s600Eq3JimtQP36IKckr0TJ4",
                "https://drive.google.com/uc?id=1UEXutnwkwOv9jr9YctQh053JyMDZZsaD",
                "https://drive.google.com/uc?id=1Ty7XbN5b25r4-k7fFnz9e7oOFGwe8ToU",
                "https://drive.google.com/uc?id=1Kf2tBo5jOxTOxqb6qysMUBB2wqyhLxKK",
                "https://drive.google.com/uc?id=1TO9CXfTMI7hJhdVEMXC9yF9cFGA7Fqcy"
        };


        for (int i = 0; i<100; i++){
            int rnd[] = {
                    rndInt(2),
                    rndInt(fpfp.length),
                    rndInt(10),
                    (rndInt(4)+1),
            };

            AccessLevel accessLevel;

            if(rnd[2]==0){
                accessLevel = accessLevelRepository.findById((long)rnd[3]).get();
            }else{
                accessLevel = accessLevelRepository.findById(4l).get();
            }

            System.out.println(accessLevel.getName());

            if(rnd[0]==0){
                userRepository.save(User.builder()
                        .username(lorem.getNameFemale())
                        .pfp(fpfp[rnd[1]])
                        .accessLevel(accessLevel)
                        .deleted(false)
                        .creationDate(new Date())
                        .lastModificationDate(new Date())
                        .build());
            }else{
                userRepository.save(User.builder()
                        .username(lorem.getNameMale())
                        .pfp(mpfp[rnd[1]])
                        .accessLevel(accessLevel)
                        .deleted(false)
                        .creationDate(new Date())
                        .lastModificationDate(new Date())
                        .build());
            }

        }

    }

    @Test
    @Transactional
    @Rollback(value = false)
    void PostLoads(){

        List<User> users = userRepository.findAll();
        List<Category> categories = categoryRepository.findAll();
        List<PostType> postTypes = postTypeRepository.findAll();

        List<Post> posts = new ArrayList<>();

        for (int i = 0; i<200; i++){
            int rndU = rndInt(users.size());
            int rndT = rndInt(postTypes.size());
            int rndC = rndInt(4);
            BigInteger rndk = BigInteger.valueOf((long)(Math.random()*610));

            BigDecimal recompensa = BigDecimal.valueOf(0.0);
            if(rndT == 2){
                recompensa = BigDecimal.valueOf(Math.random()*25);
            }

            List<Category> postCategories = new ArrayList<>();

            for(int j=0; j<rndC; j++){
                postCategories.add(categories.get(rndInt(categories.size())));
            }

            Post tmpPost = Post.builder()
                    .title(lorem.getTitle(3,10))
                    .description(lorem.getParagraphs(1,1))
                    .categories(postCategories)
                    .user(users.get(rndU))
                    .postType(postTypes.get(rndT))
                    .categories(postCategories)
                    .recompensa(recompensa)
                    .kudos(rndk)
                    .deleted(false)
                    .creationDate(new Date())
                    .lastModificationDate(new Date())
                    .build();
            posts.add(tmpPost);
        }

        postRepository.saveAll(posts);

        List<Post> savedPosts = postRepository.findAll();
        savedPosts.forEach(post -> System.out.println(post.getTitle() + " | " + post.getDescription()  + " | " + post.getUser().getUsername()));

    }

    @Test
    @Transactional
    @Rollback(value = false)
    void postImagesLoads(){

        List<Post> savedPosts = postRepository.findAll();
        List<Image> images = new ArrayList<>();

        savedPosts.forEach(s -> {

            if (s.getImages().isEmpty()){
                String c = s.getPostType().getName();
                int rnd = rndInt(2);

                if(rnd==0){
                    if("papercraft".equals(c)){
                        images.addAll(papercraftImage(s));
                    } else if ("model".equals(c)) {
                        images.addAll(modelImage(s));
                    }else if ("quest".equals(c)){
                        images.addAll(bothImage(s));
                    }
                }else{
                        images.addAll(bothImage(s));
                }
            }
        });

        images.forEach(i -> System.out.println(i.getTitle() + " | " + i.getPost().getIdPost()));

        imageRepository.saveAll(images);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void postCommentsLoads() {
        List<Post> savedPosts = postRepository.findAll();
        List<User> savedUsers = userRepository.findAll();
        int n = savedUsers.size();

        List<Comment> comments = new ArrayList<>();

        savedPosts.forEach(s -> {

            if (s.getComments().isEmpty()) {

                for (int i=0; i < rndInt(10)+1; i++){
                    Comment comment = commentGenerator(s, savedUsers.get(rndInt(n)));
                    while (rndInt(2)!=0){
                        comments.add(comment);
                        comment = commentGenerator(s, savedUsers.get(rndInt(n)), comment);
                    }
                }
            }
        });

        commentRepository.saveAll(comments);

        commentRepository.findAll().forEach(c -> System.out.println(c.getPost().getIdPost() + " | " + c.getUser().getUsername() +  " | " + c.getText()));

    }

    @Test
    void pruebaRepo(){
        String categories[] = {"halloween", "dog"};

        Pageable paginated = PageRequest.of(0, 5, Sort.by("idPost").ascending());
        Page<Post> page = this.postRepository.findAllByPostTypeAndCategoriesPaginated("quest", categories, paginated);

        System.out.println(page.getTotalElements() + " | " + page.getTotalPages() + " | " + page.getContent().size());

        page.getContent().forEach(s -> System.out.println(s.getIdPost()  + " | " + s.getTitle()));
    }

    List<Image> papercraftImage(Post post){
        String[][][] papecraftImagesUrl = {
                {
                        {"https://drive.google.com/uc?id=14abKcnnxsgf9j1hudys_vqSS7xnurgBA", "Geometric Unicorn wall art by dgemily April 02, 2023", "Unicorn wall art by dgemily with license CC BY-NC-SA 4.0"},
                        {"https://drive.google.com/uc?id=1Hl8D8MuYj-HGI8fhxVdqQw3zDvEItySG", "Geometric Unicorn wall art by dgemily April 02, 2023", "Unicorn wall art by dgemily with license CC BY-NC-SA 4.0"}
                },
                {{"https://drive.google.com/uc?id=1ZsfbXl8psmHFuLgeSKn5X8aWJ273XNsT", "Low Poly Mask by kongorilla October 11, 2012", "Mask by kongorilla with license CC BY-NC-SA 4.0" }},
                {{"https://drive.google.com/uc?id=1ID6svEvzCOjmhqVr2yKAG-9iNEYNC9yA", "Papercraft Low Poly Fox by hudson December 14, 2018", "Fox by hudson with license CC BY-NC-SA 4.0" }},
                {
                        {"https://drive.google.com/uc?id=10Rxr9VvCL1eg38QczSmoEU91t2XePero", "Papercraft Mobius Torus by hudson January 04, 2015", "Torus by hudson with license CC BY-NC-SA 4.0"},
                        {"https://drive.google.com/uc?id=14P_w6OZkuPJoc27oCrTny3N4MqLx4bvA", "Papercraft Mobius Torus by hudson January 04, 2015", "Torus by hudson with license CC BY-NC-SA 4.0"}
                },
                {{"https://drive.google.com/uc?id=1f8YN9ljnch3ieGVQTNoaUKjFDghW4nFu", "Papercraft Fennec Fox by hudson October 11, 2015", "Fennec Fox by hudson with license CC BY-NC-SA 4.0"}}
        };

        int rndimg=rndInt(papecraftImagesUrl.length);
        List<Image> images = new ArrayList<>();

        for(int i=0; i< papecraftImagesUrl[rndimg].length; i++){
            Image tmpImage = Image.builder()
                    .src(papecraftImagesUrl[rndimg][i][0])
                    .title(papecraftImagesUrl[rndimg][i][1])
                    .alt(papecraftImagesUrl[rndimg][i][2])
                    .deleted(false)
                    .post(post)
                    .creationDate(new Date())
                    .lastModificationDate(new Date())
                    .build();

            images.add(tmpImage);
        }
        return images;
    }
    List<Image> modelImage(Post post){
        String[][][] modelImagesUrl = {
                {{"https://drive.google.com/uc?id=1NxG9OBUDQ2SXgNsjrYvmAu2qLygW_h33", "cabinet repair plate by hallo_ralph June 16, 2022", "plate by hallo_ralph with license CC BY-NC-SA 4.0"}},
                {
                        {"https://drive.google.com/uc?id=11AJHEhtbeu_050r6_DJJXJdHaklc8NBs", "Ethernet | RJ45 clip to secure/repair/fix broken tab by guss67 December 28, 2020", "tab by guss67 with license CC BY-NC-SA 4.0"},
                        {"https://drive.google.com/uc?id=1OF4hsDOVJZKtjeMDyDI5GnYuluAxSiUS", "Ethernet | RJ45 clip to secure/repair/fix broken tab by guss67 December 28, 2020", "tab by guss67 with license CC BY-NC-SA 4.0"}
                },
                {{"https://drive.google.com/uc?id=1OLz5ZDmFqFf-ZohUuj9s02nKwwRsZbDh", "Laundry Rack Repair Kit by FMMT666 October 19, 2015", "Repair Kit by FMMT666 with license CC BY-NC-SA 4.0" }},
                {{"https://drive.google.com/uc?id=1gpgOW4VVlfbBgCWrW5nRDqOQNq-NVF2u", "Cable Repair Mold by txoof August 01, 2016", "Mold by txoof with license CC BY-NC-SA 4.0" }},
        };

        int rndimg=rndInt(modelImagesUrl.length);
        List<Image> images = new ArrayList<>();

        for(int i=0; i< modelImagesUrl[rndimg].length; i++){
            Image tmpImage = Image.builder()
                    .src(modelImagesUrl[rndimg][i][0])
                    .title(modelImagesUrl[rndimg][i][1])
                    .alt(modelImagesUrl[rndimg][i][2])
                    .deleted(false)
                    .post(post)
                    .creationDate(new Date())
                    .lastModificationDate(new Date())
                    .build();

            images.add(tmpImage);
        }
        return images;
    }

    List<Image> bothImage(Post post){
        String[][][] postImagesUrl = {
                {
                        {"https://drive.google.com/uc?id=1tc1f7qwGyXJ9unAJnF532KXpdVIZZfgj", "Sitting cat low poly by Vincent6m July 14, 2018", "By Vincent6m with license CC BY-NC-SA 4.0"},
                        {"https://drive.google.com/uc?id=12KgvXbj973MwsJzhhe0nVDPoe7-FP2ry", "Sitting cat low poly by Vincent6m July 14, 2018", "By Vincent6m with license CC BY-NC-SA 4.0"},
                        {"https://drive.google.com/uc?id=1M6rlmk6uHSJGCkqyioXz_qOp-DUtlGXE", "Sitting cat low poly by Vincent6m July 14, 2018", "By Vincent6m with license CC BY-NC-SA 4.0"}
                },
                {{"https://drive.google.com/uc?id=1w_hvAmXA5m_csdWfqdt0Vn5TmK8X-Pnq", "Low Poly Heart Vase by VECTARY August 04, 2017", "Vase by VECTARY with license CC BY-NC-SA 4.0" }},
                {{"https://drive.google.com/uc?id=1Bao3X197VzEpQRewXG58-LRbQyE2yqto", "Low Poly Heart Vase by VECTARY August 04, 2017", "Vase by VECTARY with license CC BY-NC-SA 4.0" }},
                {
                        {"https://drive.google.com/uc?id=1_-1qMXD6lSKRkqD4zKxIXzEk0NUsDEH6", "Horse Papercraft by turbi January 28, 2017", "Horse Papercraft by turbi with license CC BY-NC-SA 4.0"},
                        {"https://drive.google.com/uc?id=12GrAIIw2SQ_Tk_04vPd9ODo5tu8ot5mu", "Horse Papercraft by turbi January 28, 2017", "Horse Papercraft by turbi with license CC BY-NC-SA 4.0"},
                },
                {{"https://drive.google.com/uc?id=1dcIH2WUUQJbGoadBWWb1RIWFfXu4syL9", "Low-Poly Eevee by flowalistik May 26, 2018", "Eevee by flowalistik with license CC BY-NC-SA 4.0" }},
                {{"https://drive.google.com/uc?id=1eHOLpPKItCghrscCR9jB4xy16lsSFc7h", "Low poly Drogon by KerberosFI September 05, 2018", "Drogon by KerberosFI with license CC BY-NC-SA 4.0" }},
                {{"https://drive.google.com/uc?id=1YcbiLnjmhImuAXETPkrayiwBw8ZhN5f6", "Armadillo by dogbrand August 12, 2015", "Armadillo by dogbrand with license CC BY-NC-SA 4.0" }},
                {{"https://drive.google.com/uc?id=1qSNNOqEzE2AvdQgR7_H3vv94MaOIwTfU", "Low-Poly Bulbasau by flowalistik May 11, 2014", "Bulbasau by flowalistik with license CC BY-NC-SA 4.0" }},
                {{"https://drive.google.com/uc?id=1tHHAqbYqnhI6lHU1i3vO-SquDBclleoq", "Low-Poly Charmander by flowalistik May 07, 2014", "Charmander by flowalistik with license CC BY-NC-SA 4.0" }},
                {{"https://drive.google.com/uc?id=1oP_sEL5mGOOSmz40blIyUM5NksVNLgy-", "Low Poly Fantasy Tabletop - Alliance Base Units by Davision3d January 04, 2016", "Alliance Base Units by Davision3d with license CC BY-NC-SA 4.0" }},
                {{"https://drive.google.com/uc?id=1pRZNlY9O3tPL88rAf7iQLuhVqdiIM3Jz", "Geometric Low Poly Deer Head by evanandkatelyn September 03, 2013", "Deer Head by evanandkatelyn with license CC BY-NC-SA 4.0" }},
                {{"https://drive.google.com/uc?id=1J-yzgAcuvuTy9_htwryGfHFpmvqUTqmG", "Low Poly Bison Skull V2 by Gedelgo March 19, 2015", "Skull V2 by Gedelgo with license CC BY-NC-SA 4.0" }},
                {{"https://drive.google.com/uc?id=1EmPHD4RhPXARLTKJfhQCfJfm82rDnKpt", "Low Poly Triceratops Skull by Gedelgo June 12, 2015", "Triceratops Skull by Gedelgo with license CC BY-NC-SA 4.0" }}
        };

        int rndimg=rndInt((postImagesUrl.length));
        List<Image> images = new ArrayList<>();

        for(int i=0; i< postImagesUrl[rndimg].length; i++){
            Image tmpImage = Image.builder()
                    .src(postImagesUrl[rndimg][i][0])
                    .title(postImagesUrl[rndimg][i][1])
                    .alt(postImagesUrl[rndimg][i][2])
                    .deleted(false)
                    .post(post)
                    .creationDate(new Date())
                    .lastModificationDate(new Date())
                    .build();

            images.add(tmpImage);
        }

        return images;
    }

    int rndInt(int limit){
        return (int)(Math.random()*limit);
    }

    Comment commentGenerator(Post p, User u){

        return Comment.builder()
                .post(p)
                .user(u)
                .text(lorem.getTitle(2,8))
                .kudos(BigInteger.valueOf(rndInt(100)))
                .deleted(false)
                .creationDate(new Date())
                .lastModificationDate(new Date())
                .build();
    }

    Comment commentGenerator(Post p, User u, Comment c){

        return Comment.builder()
                .post(p)
                .user(u)
                .parentComment(c)
                .text(lorem.getTitle(2,8))
                .kudos(BigInteger.valueOf(rndInt(100)))
                .deleted(false)
                .creationDate(new Date())
                .lastModificationDate(new Date())
                .build();
    }

}
