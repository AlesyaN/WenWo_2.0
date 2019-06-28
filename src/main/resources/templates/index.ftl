<#ftl encoding='UTF-8'>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Wenwo</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,500,600,700,700i|Montserrat:300,400,500,600,700"
          rel="stylesheet">
    <!-- Bootstrap CSS File -->
    <link type="text/css" href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- Libraries CSS Files -->
    <link type="text/css" href="/fonts/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link type="text/css" href="/css/animate.min.css" rel="stylesheet">
    <link type="text/css" href="/css/ionicons.min.css" rel="stylesheet">
    <link type="text/css" href="/css/owl.carousel.min.css" rel="stylesheet">
    <link type="text/css" href="/css/lightbox.min.css" rel="stylesheet">

    <!-- Main Stylesheet File -->
    <link type="text/css" href="/css/style.css" rel="stylesheet">

</head>

<body>
<!--==========================
Header
============================-->
<header id="header">

    <div id="topbar">
        <div class="container">
            <div class="social-links">
                <a href="#" class="twitter"><i class="fa fa-twitter"></i></a>
                <a href="#" class="facebook"><i class="fa fa-vk"></i></a>
                <a href="#" class="instagram"><i class="fa fa-instagram"></i></a>
                <a href="#" class="instagram"><i class="fa fa-telegram"></i></a>
            </div>
        </div>
    </div>

    <div class="container">

        <div class="logo float-left">
            <!-- Uncomment below if you prefer to use an image logo -->
            <h1 class="text-light"><a href="#intro" class="scrollto"><span>Wenwo</span></a></h1>
            <!-- <a href="#header" class="scrollto"><img src="img/logo.png" alt="" class="img-fluid"></a> -->
        </div>

        <nav class="main-nav float-right d-none d-lg-block">
            <ul>
                <li class="active"><a href="#intro">Home</a></li>
                <li><a href="#why-us">About Us</a></li>
                <li><a href="#services">Search</a></li>
                <li><a href="#testimonials">Team</a></li>
                <li><a href="#footer">Contact Us</a></li>
            </ul>
        </nav><!-- .main-nav -->

    </div>
</header><!-- #header -->

<!--==========================
  Intro Section
============================-->
<section id="intro" class="clearfix">
    <div class="container d-flex h-100">
        <div class="row justify-content-center align-self-center">
            <div class="col-md-6 intro-info order-md-first order-last">
                <h2>Get to know<br>each other better!</h2>
                <div>
                    <a href="/login" class="btn-get-started scrollto">Get started</a>
                    <a href="#why-us" class="btn-get-started scrollto" style="">learn more</a>
                </div>

            </div>

            <div class="col-md-6 intro-img order-md-last order-first">
                <img src="/images/intro-img.png" alt="" class="img-fluid">
            </div>
        </div>

    </div>
</section><!-- #intro -->

<main id="main">




    <!--==========================
      Why Us Section
    ============================-->
    <section id="why-us" class="wow fadeIn">
        <div class="container-fluid">

            <header class="section-header">
                <h3>About us</h3>
                <p>WenWo is not just "the another one social network". Why it is so?</p>
            </header>

            <div class="row">

                <div class="col-lg-6">
                    <div class="why-us-img">
                        <img src="/images/why-us.jpg" alt="" class="img-fluid">
                    </div>
                </div>

                <div class="col-lg-6">
                    <div class="why-us-content">
                        <p>Today each of us has so many accounts in different social networks. But all of them are based
                        on the same concept of sending messages and sharing public posts, which made people even more private: we chat
                        only with our close friends and browse our feed in a read-only mode without having any communication with other users.</p>
                        <p>
                            WenWo application is based on a different concept.
                            The key point of our project is a <strong>question</strong>.
                        </p>

                        <div class="features wow bounceInUp clearfix">
                            <i class="fa fa-diamond" style="color: #f058dc;"></i>
                            <h4>Questions & Answers</h4>
                            <p>User can ask somebody public or private question to know him better.
                                When the second user answers this question, it appears on his page and in the feed of the followers.
                                It is just like an interview for every user with questions from his friends.</p>
                        </div>

                        <div class="features wow bounceInUp clearfix">
                            <i class="fa fa-object-group" style="color: #ffb774;"></i>
                            <h4>Chats & Albums</h4>
                            <p>However, there are common social networks options such as sharing photos and chatting.</p>
                        </div>

                        <div class="features wow bounceInUp clearfix">
                            <i class="fa fa-language" style="color: #589af1;"></i>
                            <h4>Global focus</h4>
                            <p>Nowadays, there are several social networks with the same concept of Q&A. But WenWo is the first such <strong>worldwide</strong> application.</p>
                        </div>

                    </div>

                </div>

            </div>

        </div>

        <div class="container">
            <div class="row counters">
                <#if info??>
                <div class="col-lg-3 col-6 text-center">
                    <span data-toggle="counter-up">${info.users}</span>
                    <p>Users</p>
                </div>

                <div class="col-lg-3 col-6 text-center">
                    <span data-toggle="counter-up">${info.questions}</span>
                    <p>Questions</p>
                </div>

                <div class="col-lg-3 col-6 text-center">
                    <span data-toggle="counter-up">${info.anonymousQuestions}</span>
                    <p>Anonymous questions</p>
                </div>

                <div class="col-lg-3 col-6 text-center">
                    <span data-toggle="counter-up">${info.answers}</span>
                    <p>Answers</p>
                </div>
                </#if>
            </div>

        </div>
    </section>

    <!--==========================
      Call To Action Section
    ============================-->
    <section id="call-to-action" class="wow fadeInUp">
        <div class="container">
            <div class="row">
                <div class="col-lg-9 text-center text-lg-left">
                    <h3 class="cta-title">Haven't registered yet?</h3>
                    <p class="cta-text">If you are not registered, you only can search for friend's profiles and view them.
                        Registered user has it's own profile with Personal Info, Albums and Questions; it also can ask anyone a question, answer on users questions to him, leave comments, likes, follow someone and chat with other users. </p>
                </div>
                <div class="col-lg-3 cta-btn-container text-center">
                    <a class="cta-btn align-middle" href="/signUp">Sign up right now</a>
                </div>
            </div>

        </div>
    </section><!-- #call-to-action -->

    <!--==========================
      Services Section
    ============================-->
    <section id="services" class="section-bg">
        <div class="container">

            <header class="section-header">
                <h3>Search</h3>
                <p>Find your friends, check their feed and ask them questions!</p>
            </header>

            <div class="row">

                <div class="wow bounceInUp" style="margin: auto" data-wow-duration="1.4s">
                    <div class="box">
                        <div class="icon" style="background: #FCF5B9;"><i class="fa fa-search"
                                                                          style="color: #F9CD0B;"></i></div>

                        <form class="form-inline" method="get" action="/search">
                            <input class="form-control mx-sm-3 mb-3" style="width: 800px" type="text" placeholder="enter your friend nickname">
                            <button class="btn btn-light mb-3" type="submit">Search</button>
                        </form>
                    </div>
                </div>


            </div>

        </div>
    </section><!-- #services -->
    <!--==========================
      Clients Section
    ============================-->
    <section id="testimonials">
        <div class="container">

            <header class="section-header">
                <h3>Our team</h3>
            </header>

            <div class="row justify-content-center">
                <div class="col-lg-10">

                    <div class="owl-carousel testimonials-carousel wow fadeInUp">

                        <div class="testimonial-item">
                            <img src="/images/testimonial-3.jpg" class="testimonial-img" alt="">
                            <h3>Alesya Nasibullina</h3>
                            <h4>Backend</h4>
                            <p>
                                Enim nisi quem export duis labore cillum quae magna enim sint quorum nulla quem veniam
                                duis minim tempor labore quem eram duis noster aute amet eram fore quis sint minim.
                            </p>
                        </div>

                        <div class="testimonial-item">
                            <img src="/images/testimonial-4.jpg" class="testimonial-img" alt="">
                            <h3>Kamila Nigmetzyanova</h3>
                            <h4>Frontend</h4>
                            <p>
                                Fugiat enim eram quae cillum dolore dolor amet nulla culpa multos export minim fugiat
                                minim velit minim dolor enim duis veniam ipsum anim magna sunt elit fore quem dolore
                                labore illum veniam.
                            </p>
                        </div>

                    </div>

                </div>
            </div>


        </div>
    </section><!-- #testimonials -->


</main>

<!--==========================
  Footer
============================-->
<footer id="footer" class="section-bg">
    <div class="footer-top">
        <div class="container">

            <div class="row">

                <div class="col-lg-6">

                    <div class="row">

                        <div class="col-sm-10   ">

                            <div class="footer-info">
                                <h3>Wenwo</h3>
                                <p>Cras fermentum odio eu feugiat lide par naso tierra. Justo eget nada terra videa
                                    magna derita valies darta donna mare fermentum iaculis eu non diam phasellus.
                                    Scelerisque felis imperdiet proin fermentum leo. Amet volutpat consequat mauris nunc
                                    congue.</p>
                            </div>


                            <div class="footer-links">
                                <h4>Contact Us</h4>
                                <p>
                                    21 Kremlevskaya Street<br>
                                    Kazan, 421001<br>
                                    Russia <br>
                                    <strong>Phone:</strong> +7 917 909 35 75<br>
                                    <strong>Email:</strong> kamila.nigmet@gmail.com<br>
                                </p>
                            </div>

                            <div class="social-links">
                                <a href="#" class="twitter"><i class="fa fa-twitter"></i></a>
                                <a href="#" class="facebook"><i class="fa fa-vk"></i></a>
                                <a href="#" class="instagram"><i class="fa fa-instagram"></i></a>
                                <a href="#" class="linkedin"><i class="fa fa-telegram"></i></a>
                            </div>

                        </div>

                    </div>

                </div>

                <div class="col-lg-6">

                    <div class="form">

                        <h4>Send us a message</h4>
                        <p>Eos ipsa est voluptates. Nostrum nam libero ipsa vero. Debitis quasi sit eaque numquam
                            similique commodi harum aut temporibus.</p>
                        <form action="" method="post" role="form" class="contactForm">
                            <div class="form-group">
                                <input type="text" name="name" class="form-control" id="name" placeholder="Your Name"
                                       data-rule="minlen:4" data-msg="Please enter at least 4 chars"/>
                                <div class="validation"></div>
                            </div>
                            <div class="form-group">
                                <input type="email" class="form-control" name="email" id="email"
                                       placeholder="Your Email" data-rule="email"
                                       data-msg="Please enter a valid email"/>
                                <div class="validation"></div>
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" name="subject" id="subject"
                                       placeholder="Subject" data-rule="minlen:4"
                                       data-msg="Please enter at least 8 chars of subject"/>
                                <div class="validation"></div>
                            </div>
                            <div class="form-group">
                                <textarea class="form-control" name="message" rows="5" data-rule="required"
                                          data-msg="Please write something for us" placeholder="Message"></textarea>
                                <div class="validation"></div>
                            </div>

                            <div id="sendmessage">Your message has been sent. Thank you!</div>
                            <div id="errormessage"></div>

                            <div class="text-center">
                                <button type="submit" title="Send Message">Send Message</button>
                            </div>
                        </form>
                    </div>

                </div>


            </div>

        </div>
    </div>

    <div class="container">
        <div class="copyright">
            &copy; Copyright <strong>Rapid</strong>. All Rights Reserved
        </div>
        <div class="credits">
            <!--
              All the links in the footer should remain intact.
              You can delete the links only if you purchased the pro version.
              Licensing information: https://bootstrapmade.com/license/
              Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/buy/?theme=Rapid
            -->
            Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
        </div>
    </div>
</footer><!-- #footer -->

<a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>
<!-- Uncomment below i you want to use a preloader -->
<!-- <div id="preloader"></div> -->

<!-- JavaScript Libraries -->
<script type="text/javascript" src="/js/jquery-3.2.1.min.js"
<script type="text/javascript" src="/js/lib/bootstrap/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="/js/lib/easing/easing.min.js"></script>
<script type="text/javascript" src="/js/lib/mobile-nav/mobile-nav.js"></script>
<script type="text/javascript" src="/js/lib/wow/wow.min.js"></script>
<script type="text/javascript" src="/js/lib/waypoints/waypoints.min.js"></script>
<script type="text/javascript" src="/js/lib/counterup/counterup.min.js"></script>
<script type="text/javascript" src="/js/lib/owlcarousel/owl.carousel.min.js"></script>
<script type="text/javascript" src="/js/lib/isotope/isotope.pkgd.min.js"></script>
<script type="text/javascript"  src="/js/lib/lightbox/js/lightbox.min.js"></script>
<!-- Contact Form JavaScript File -->
<script type="text/javascript" src="contactform/contactform.js"></script>

<!-- Template Main Javascript File -->
<script type="text/javascript" src="/js/main.js"></script>

<script type="text/javascript" src="/js/index.js"></script>

</body>
</html>