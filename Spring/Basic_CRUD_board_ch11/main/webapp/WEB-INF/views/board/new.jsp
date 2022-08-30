<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>register</title>

    <link rel="shortcut icon" href="/resources/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/favicon.ico" type="image/x-icon">

    <style>
        #wrapper{
            width: 1024px;
            margin:0 auto;

            font-family: D2Coding;
            font-size: 16px;

        }
        #submitBtn{
            width:130px;
            height: 40px;
            
            border:0;
            
            font-family: D2Coding;
            font-size: 14px;
            font-weight: bold;
            background-color: green;
            color:white;
            cursor:pointer;
        }
        #listBtn{
            width:130px;
            height: 40px;
            
            border:0;
            
            font-family: D2Coding;
            font-size: 14px;
            font-weight: bold;
            background-color: pink;
            color:white;
            cursor:pointer;
        }
    </style>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>
    <script>
        $(function(){
            console.clear();

            $('#listBtn').on('click',function(){
                location.href="/board/list";
            }); //.onclick

        })
    </script>


</head>
<body>

    <h1>/WEB_INF/views/board/new.jsp</h1>
    <hr>

    <div id="wrapper">
        <form action="/board/register" method="POST">
            <table>
                <tr>
                    <td><label for="title">제목</label></td>
                    <td><input type="text" id="title" name="title" size="50" placeholder="제목을 입력하세요" required></td>
                </tr>

                <tr>
                    <td><label for="title">내용</label></td>
                    <td><textarea name="content" id="content" cols="52" rows="10" placeholder="내용을 입력하세요"  required></textarea></td>
                </tr>

                <tr>
                    <td><label for="writer">작성자</label></td>
                    <td><input type="text" id="writer" name="writer" size="20" placeholder="작성자"  required></td>
                </tr>

                <tr>
                    <td colspan="2">&nbsp;</td>
                </tr>

                <tr>
                    <td colspan="2">
                        <button type="submit" id="submitBtn">SUBMIT</button>
                        <button type="button" id="listBtn">LIST</button>
                    </td>

                </tr>
            </table>
        </form>
</div>

   
</body>
</html>