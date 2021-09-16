<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Web-analysis</title>

    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/2015/bootstrap3/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/font-awesome/4-4-0/font-awesome.min.css"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css"
          integrity="sha384-3AB7yXWz4OeoZcPbieVW64vVXEwADiYyAEhwilzWsLw+9FgqpyjjStpPnpBO8o8S" crossorigin="anonymous">


    <style language="css" type="text/css">
        <%@include file="/WEB-INF/css/main.css"%>
    </style>

    <script language="JavaScript" type="text/javascript">
        <%@include file="/WEB-INF/scripts/main.js"%>
    </script>


    <script src="https://npmcdn.com/particlesjs@2.2.2/dist/particles.min.js"></script>


</head>

<body>



<canvas class="background"></canvas>

<br>

<div class="container">
    <div class="row">



        <div class="col-md-10 col-sm-10 col-md-offset-1">
            <div class="serviceBox">
                <div class="service-content">
                    <h3>WEB-parsing for RENZO</h3>
                    <p>Добро пожаловать в приложение позволяющее облегчить работу производителям и продавцам
                        автомобильных компонентов. Данное приложение позволяет собирать информацию по конкурентам (с
                        сайтов emex.ru, adeo.pro, autodoc.ru, parts66.ru, autopiter.ru, euroauto.ru, exist.ru при необходимости, список можно расширять).</p>
                </div>
            </div>
        </div>

    </div><!-- ./row -->
</div>

<br>

<div class="container">
    <div class="row">


        <div  class="col-md-10 col-sm-10 col-md-offset-1">
            <div class="serviceBox">
                <div class="service-icon">
                    <i class="fa fa-rocket"></i>
                </div>
                <div class="service-content">
                    <h3><a href="parsing">Web parsing</a></h3>
                    <p>Произвести сбор информации по конкурентам (наличие на складах, цены, аналоги)</p>
                </div>
                <div class="read">
                    <a href="parsing" class="btn btn-default">Перейти</a>
                </div>
            </div>
        </div>



    </div><!-- ./row -->
</div>


</body>
</html>