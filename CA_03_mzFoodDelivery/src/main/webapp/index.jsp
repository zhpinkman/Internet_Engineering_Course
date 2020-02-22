<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>MZ Food Delivery</title>
    <style>
        :root {
            --doorColor: #54969a;
            --glassColor: #afcfd7;
            --letterColor: #f4f1bb;
            --letterShadow: #868466;
            --wallColor: #ed6a5a;
            --wallDark: #ad4e42;
        }
        body {
            background: var(--letterShadow);
        }
        .box {
            position: relative;
            display: block;
            margin: auto;
            margin-top: 8%;
            width: 600px;
            height: 420px;
            background: none;
        }
        .wall {
            position: absolute;
            width: 80%;
            height: 70%;
            left: 10%;
            bottom: 10%;
            background-color: var(--wallColor);
            background-image: linear-gradient(
                    270deg,
                    var(--wallColor) 15%,
                    var(--wallDark) 15%,
                    var(--wallColor) 50%,
                    var(--wallDark) 85%,
                    var(--wallColor) 85%
            );
            z-index: 1;
        }
        .line1,
        .line2,
        .line3 {
            position: absolute;
            width: 110%;
            height: 8%;
            left: -5%;
            background: var(--letterColor);
            z-index: 2;
        }
        .line1 {
            bottom: -8%;
        }
        .line2 {
            bottom: 50%;
        }
        .line3 {
            top: -8%;
        }
        .door {
            position: absolute;
            width: 25%;
            height: 40%;
            bottom: 0%;
            left: 37.5%;
            background: var(--glassColor);
            border: 8px solid var(--doorColor);
            border-bottom: none;
            z-index: 2;
        }
        .line4 {
            position: absolute;
            width: 5%;
            height: 100%;
            left: 45%;
            bottom: 0%;
            background: var(--glassColor);
            border: 3px solid var(--doorColor);
            border-top: none;
            border-bottom: none;
            z-index: 2;
        }
        .window1,
        .window2 {
            position: absolute;
            width: 14%;
            height: 30%;
            bottom: 10%;
            background: var(--glassColor);
            border-radius: 50px 50px 0px 0px;
            border: 3px solid var(--doorColor);
            z-index: 2;
        }
        .window1 {
            left: 10%;
        }
        .window2 {
            right: 10%;
        }
        .text {
            position: absolute;
            font-family: "Roboto Slab", serif;
            font-size: 50px;
            left: 12%;
            top: 12%;
            font-weight: bold;
            color: var(--letterColor);
            letter-spacing: 2px;
            text-shadow: 3px 2px var(--letterShadow);
        }
    </style>
</head>
<body>
<div style="text-align: center;">
    .: MZ Food Delivery :.
    <hr>
    <a href='getRestaurants'>.: Restaurants near you :.</a>
    <hr>
    <a href='profile'>.: Profile :.</a>
    <hr>
    <a href='cart'>.: Cart :.</a>
    <hr>
</div>
<div class="box">
    <div class="wall">
        <div class="line1"></div>
        <div class="line2"></div>
        <div class="line3"></div>
        <div class="door">
            <div class="line4"></div>
        </div>
        <div class="window1">
            <div class="line4"></div>
        </div>
        <div class="window2">
            <div class="line4"></div>
        </div>
        <div class="text">RESTAURANT</div>
    </div>
</div>
</body>
</html>