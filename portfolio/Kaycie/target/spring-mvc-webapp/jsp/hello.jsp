<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
        
                
    </head>
    <body>
        <div>
            <header style="height:150px;">
                <div class="theme-header-wrapper">
                    <div class="theme-header-row">
                        <div class="theme-secondary-header">
                            <div class="theme-row">
                                <div class="theme-alignright">
                                    <div class='theme-contact-info'>
                                        <span class='theme-contact-info-phone-number'>
                                            <a href='tel:651-300-3688'>651-300-3688</a>
                                        </span>
                                        <span class='theme-contact-info-seperator'>|</span>
                                        <span class='theme-contact-info-email'>
                                            <a href='mailto:kaycie.m.dale@gmail.com'>Kaycie.M.Dale@gmail.com</a>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            
                        </div>

                    </div>
                    <div class='theme-row'>
                        <div id="logo" class="col-lg-6">
                            <a href="/"><img src="${pageContext.request.contextPath}/images/Kaycielogo.jpg"></a>
                        </div>
                        <div class="navbar theme-alignright col-lg-6">
                            <ul class="nav nav-tabs">
                                <li role="presentation" class="active">
                                    <a href="/">Home</a>
                                </li>
                                <li role="presentation">
                                    <a href="/about-me">About Me</a>
                                </li>
                                <li role="presentation">
                                    <a href="/services">Services</a>
                                </li>
                                <li role="presentation">
                                    <a href="/faqs">FAQs</a>
                                </li>
                                <li role="presentation">
                                    <a href="/forms">Forms</a>
                                </li>
                                <li role="presentation">
                                    <a href="/contact">Contact</a>
                                </li>
                            </ul>    
                        </div>
                    </div>
                </div>    
                
                
            </header>
            
            <div class=" row theme-row">
                <div class="col-lg-4">
                    
                        <img src="headshot.jpg" alt="Kaycie Dale"> 
                </div>
                <div class='col-lg-8'>
                    <h1>About Me</h1>
                     <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                         Duis nec venenatis magna, sed tristique ligula. Aenean 
                         venenatis lacinia placerat. Maecenas quis odio convallis, 
                         luctus nisi eget, varius libero. Integer vehicula 
                         placerat turpis at bibendum. Donec eget maximus sem. In 
                         aliquam ultrices augue id tincidunt. Nam vulputate 
                         suscipit dolor vitae consectetur. Cras vestibulum in 
                         justo nec suscipit.</p>
                     <p>
                         Aliquam non nunc nec nunc dictum vulputate id at tellus. 
                         Nulla elementum odio vitae tortor fermentum, at sagittis 
                         nisi consequat. Suspendisse potenti. Fusce et sagittis 
                         est. Aliquam vel orci nulla. Pellentesque quis 
                         condimentum erat. Interdum et malesuada fames ac ante 
                         ipsum primis in faucibus. Vivamus lacinia tristique 
                         nisl. In tincidunt in risus sit amet dignissim. Sed 
                         semper fermentum maximus. Integer neque ligula, 
                         volutpat quis risus in, placerat dapibus ligula. Mauris 
                         condimentum purus semper lorem cursus, in molestie leo 
                         hendrerit. Fusce sed tempor nisl.
                     </p>
                     <p>
                         Nam non magna ac dolor blandit interdum. Praesent 
                         ultricies vel quam sit amet bibendum. Suspendisse tempus 
                         turpis sed augue pharetra molestie. Aliquam lacus enim, 
                         facilisis aliquam hendrerit ac, commodo a elit. Quisque 
                         dolor libero, tempor nec pretium ut, congue eget tortor. 
                         Donec nec tellus at felis suscipit dictum. Donec magna 
                         turpis, consectetur at risus nec, maximus tempus sapien. 
                         Aliquam velit ligula, accumsan ac mi at, fermentum 
                         gravida est. Cras convallis non diam vitae blandit. 
                         Nulla facilisi. Donec leo ante, efficitur non dolor 
                         non, tempor ornare nibh. Maecenas non facilisis ante. 
                         Pellentesque libero urna, sollicitudin non velit eu, 
                         vehicula imperdiet felis. Nulla eu ante vitae ex luctus 
                         feugiat. 
                     </p>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

