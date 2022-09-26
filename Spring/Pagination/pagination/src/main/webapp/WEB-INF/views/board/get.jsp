<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>list</title>
    
    <script>
    	var result="${param.result}";
    	if(result != null && result.length>0){
    		alert(result);
    	}
    </script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>
    <script>
        $(function(){
            console.clear();

            $('#listBtn').on('click',function(){
                location.href="/board/list?currPage=${cri.currPage}";
            }); //.onclick

            $('#modifyBtn').click( ()=> {
                location.href="/board/modify?bno=${board.bno}&currPage=${cri.currPage}";
            });

        })
    </script>
    <style>
        #container{
            width: 1024px;
            margin: 0 auto;
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

        #modifyBtn{
            width:130px;
            height: 40px;
            
            border:0;
            
            font-family: D2Coding;
            font-size: 14px;
            font-weight: bold;
            background-color: greenyellow;
            color:white;
            cursor:pointer;
        }
        caption{
            font-size: 20px;
            font-weight: bold;

            color:blue;
        }
    </style>
</head>
<body>
    <h1>/WEB-INF/views/board/get.jsp</h1>
    <hr>
    

    <div id="container">
        <form action="#" method="POST">
            <table>
                <caption><h3>게시글 번호: ${board.bno}</h3></caption>
                <tr>
                    <td><label for="title">제목</label></td>
                    <td><input type="text" id="title" name="title" size="50" value="${board.title}" readonly></td>
                </tr>

                <tr>
                    <td><label for="title">내용</label></td>
                    <td><textarea name="content" id="content" cols="52" rows="10" readonly>${board.content}</textarea></td>
                </tr>

                <tr>
                    <td><label for="writer">작성자</label></td>
                    <td><input type="text" id="writer" name="writer" size="20" value="${board.writer}" readonly></td>
                </tr>

                <tr>
                    <td colspan="2">&nbsp;</td>
                </tr>

                <tr>
                    <td colspan="2">
                        <button type="button" id="modifyBtn">MODIFY</button>
                        <button type="button" id="listBtn">LIST</button>
                    </td>

                </tr>
            </table>
        </form>
</div>
</body>
</html>
