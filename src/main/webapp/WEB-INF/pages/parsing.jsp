<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"
         isELIgnored="false" %>
<% request.setCharacterEncoding("UTF-8");%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>

    <meta charset="utf-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<%--    <%@page import="my.parsing.app.servlet.ParsingServletJava" %>--%>

    <title>Web-parsing for RENZO</title>

    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/2015/bootstrap3/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/font-awesome/4-4-0/font-awesome.min.css"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css"
          integrity="sha384-3AB7yXWz4OeoZcPbieVW64vVXEwADiYyAEhwilzWsLw+9FgqpyjjStpPnpBO8o8S" crossorigin="anonymous">


    <style language="css" type="text/css">
        <%@include file="/WEB-INF/css/parsing.css"%>
    </style>


    <script language="JavaScript" type="text/javascript">
        <%@include file="/WEB-INF/scripts/parsing.js"%>
    </script>


    <script src="https://npmcdn.com/particlesjs@2.2.2/dist/particles.min.js"></script>


</head>
<body>

<canvas class="background"></canvas>

<br>



<div style="text-align: center;" class="container">
    <div style="text-align: center;" class="row">



        <div style="text-align: center;" class="col-md-10 col-sm-10 col-md-offset-1">
            <div>
                <div class="service-content">

                    <a href="main" class="btn btn-default">MAIN</a>

                </div>
            </div>
        </div>


    </div>
</div>

<br>

<div class="container">
    <div class="row">




        <div class="col-md-10 col-sm-10 col-md-offset-1">
            <div class="serviceBox">
                <div class="service-content">


                    <div class="service-icon">
                        <i class="fa fa-rocket"></i>
                    </div>
                    <div class="service-content">
                        <h3>Web parsing for RENZO</h3>
                        <p>Для парсинга информации заполните поля указанные ниже. Процесс
                            парсинга может занимать 30-120 сек для одной позиции в зависимости от объёма информации (вся
                            информация записывается в формате CSV).</p>
                    </div>


                    <table border="1" align="center">

                        <form method="post" action="parsing" enctype="multipart/form-data">
                            <tr>
                                <td>Загрузите файл в формате csv для последующей обрабоки</td>
                                <td><input type="file" name="file"/></td>
                                <td><input type="submit" value="Upload"/>
                            </tr>
                        </form>

                        <form name="formIndex" method="post" action="startparsing">
                            <tr>
                                <td>Выберите файл загруженный ранее (исходник для парсинга)</td>
                                <td colspan="2">
                                    <select class="selectpicker" name="arrUploadsFiles" id='arrUploadsFiles'>
                                        <c:forEach var="arrUploadsFiles" items="${arrUploadsFiles}">
                                            <option><c:out value="${arrUploadsFiles}"/></option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>

                            <tr>
                                <td>Введите ссылку/папку куда выгружать файла CSV с ценами (в формате D:\app и тп)</td>
                                <td colspan="2"><input name="fileLinkCsvOut" id="fileLinkCsvOut" type="text" size="50"></td>
                            </tr>

                            <tr>
                                <td>Выберите сайт для парсинга</td>
                                <td colspan="2">
                                    <select name="siteSelect" id='siteSelect' onchange="showNames(this.value)"
                                            width="50">
                                        <option selected disabled hidden style='display: none' value=''></option>
                                        <option value="AutoDoc">AutoDoc</option>
                                        <option value="Autopiter">Autopiter</option>
                                        <option value="Adeo">Adeo</option>
                                       <%-- <option value="Amtel">Amtel</option>--%>
                                        <option value="Emex">Emex</option>
                                        <option value="Euroauto">Euroauto</option>
                                        <option value="EuroautoOpt">EuroautoOpt</option>
                                        <option value="Exist">Exist</option>
                                        <option value="ExistQuick">ExistQuick</option>
                                        <option value="ExistWithPass">ExistWithPass</option>
                                        <option value="ExistQuickWithPass">ExistQuickWithPass</option>
                                      <%--  <option value="ExistUkr">ExistUkr</option>--%>
                                <%--        <option value="TecDoc">TecDoc</option>
                                        <option value="TecDocCross">TecDocCross</option>
                                        <option value="TecDocCrossAnalog">TecDocCrossAnalog</option>--%>
                                        <option value="Parts66">Parts66</option>

                                    </select>
                                </td>
                            </tr>

                            <tr>
                                <td>Интервал между запросами (в секундах (от 1 до 50))</td>
                                <td colspan="2" align="center">

                                    <b><input type="number" style="text-align: center" name="intervalZap" id="intervalZap" value="2" min="1" max="50" step="1" title="Минимальное значение = 1, максимальное = 50."></b>

                                </td>
                            </tr>


                            <tr>
                                <td>Количество запросов между которыми делать перерыв (от 1 до 1000)</td>
                                <td colspan="2" align="center">

                                    <b><input type="number" style="text-align: center" align="right" name="countPer" id="countPer" value="1000" min="1" max="1000" step="1" title="Минимальное значение = 1, максимальное = 1000."></b>

                                </td>
                            </tr>

                            <tr>
                                <td>Время между перерывами (в минутах (от 1 до 1440)</td>
                                <td colspan="2" align="center">

                                    <b><input type="number" style="text-align: center"  align="center" name="timePer" id="timePer" value="1" min="1" max="1440" step="1" title="Минимальное значение = 1, максимальное = 1440."></b>

                                </td>
                            </tr>


                            <tr>
                                <td align="center" colspan="3"><input type="submit" value="Запустить парсинг"
                                                                      align="center" onclick="showresult()"></td>

                                <form method="post" action="parsing" id="form1">
                                    <div id="777" hidden="hidden"></div>
                                </form>

                            </tr>
                        </form>
                    </table>


                </div>
            </div>
        </div>


    </div><!-- ./row -->
</div>


</body>
</html>
