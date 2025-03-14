<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
    <link rel="stylesheet" href="./css/style.css">
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Quicksand:wght@300;400;500;600;700&display=swap');
        *
        {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Quicksand', sans-serif;
        }
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background: #181818;
            overflow: hidden;
        }

        .grid-background {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            display: grid;
            grid-template-columns: repeat(20, 1fr);
            grid-template-rows: repeat(10, 1fr);
            gap: 1px;
            z-index: 1;
        }

        .grid-background div {
            width: 100%;
            height: 100%;
            background: rgba(255, 255, 255, 0.1);
            transition: background 0.3s, transform 0.2s;
        }

        .grid-background div:hover {
            background: rgba(0, 255, 0, 0.5);
            transform: scale(1.1);
        }

        .signin {
            position: absolute;
            z-index: 10;
        }
        section
        {
            position: absolute;
            width: 100vw;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 2px;
            flex-wrap: wrap;
            overflow: hidden;
        }


        section span
        {
            position: relative;
            display: block;
            width: calc(6.25vw - 2px);
            height: calc(6.25vw - 2px);
            background: #181818;
            z-index: 2;
            transition: 1.5s;
        }
        section span:hover
        {
            background: #0f0;
            transition: 0s;
        }

        section .signin
        {
            position: absolute;
            width: 400px;
            background: #222;
            z-index: 1000;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 40px;
            border-radius: 4px;
            box-shadow: 0 15px 35px rgba(0,0,0,9);
        }
        section .signin .content
        {
            position: relative;
            width: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            gap: 40px;
        }
        section .signin .content h2
        {
            font-size: 2em;
            color: #0f0;
            text-transform: uppercase;
        }
        section .signin .content .form
        {
            width: 100%;
            display: flex;
            flex-direction: column;
            gap: 25px;
        }
        section .signin .content .form .inputBox
        {
            position: relative;
            width: 100%;
            margin-bottom: 26px;
        }
        section .signin .content .form .inputBox input
        {
            position: relative;
            width: 100%;
            background: #333;
            border: none;
            outline: none;
            padding: 25px 10px 7.5px;
            border-radius: 4px;
            color: #fff;
            font-weight: 500;
            font-size: 1em;
        }
        section .signin .content .form .inputBox i
        {
            position: absolute;
            left: 0;
            padding: 15px 10px;
            font-style: normal;
            color: #aaa;
            transition: 0.5s;
            pointer-events: none;
        }
        .signin .content .form .inputBox input:focus ~ i,
        .signin .content .form .inputBox input:valid ~ i
        {
            transform: translateY(-7.5px);
            font-size: 0.8em;
            color: #fff;
        }
        .signin .content .form .links
        {
            position: relative;
            width: 100%;
            display: flex;
            justify-content: space-between;
        }
        .signin .content .form .links a
        {
            color: #fff;
            text-decoration: none;
        }
        .signin .content .form .links a:nth-child(2)
        {
            color: #0f0;
            font-weight: 600;
        }
        .signin .content .form .inputBox input[type="submit"]
        {
            padding: 10px;
            background: #0f0;
            color: #000;
            font-weight: 600;
            font-size: 1.35em;
            letter-spacing: 0.05em;
            cursor: pointer;
        }
        input[type="submit"]:active
        {
            opacity: 0.6;
        }
        @media (max-width: 900px)
        {
            section span
            {
                width: calc(10vw - 2px);
                height: calc(10vw - 2px);
            }
        }
        @media (max-width: 600px)
        {
            section span
            {
                width: calc(20vw - 2px);
                height: calc(20vw - 2px);
            }
        }
    </style>
</head>
<body>
<section>
    <div class="signin">
        <div class="content">
            <h2>Sign In</h2>
            <div class="form">
                <% if (request.getParameter("error") != null) { %>
                <p class="error-message">
                    <% if ("1".equals(request.getParameter("error"))) { %>
                    Sai tên đăng nhập hoặc mật khẩu!
                    <% } else if ("2".equals(request.getParameter("error"))) { %>
                    Tài khoản bị khóa!
                    <% } %>
                </p>
                <% } %>
                <form action="LoginServlet" method="post">
                    <div class="inputBox">
                        <input type="text" name="username" required>
                        <i>Username</i>
                    </div>
                    <div class="inputBox">
                        <input type="password" name="password" required>
                        <i>Password</i>
                    </div>

                    <div class="inputBox">
                        <input type="submit" value="Login">
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
</body>
</html>
