# Internet_Engineering_Course

for having farsi characters (UTF-8) in servlet responses and requests you can use this :

>       response.setContentType("text/html; charset=UTF-8");
>       response.setCharacterEncoding("UTF-8");
>       PrintWriter out = response.getWriter();
