<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>list</title>
    
    <link rel="shortcut icon" href="/resources/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/favicon.ico" type="image/x-icon">

    <style>
        *{
            margin: 0 auto;
            padding: 0;
        }

        #wrapper{
            width:1024px;

            font-family: D2Coding;
            font-size: 16px;
        }

        table{
            width:100%;

            text-align:center;

            border-collapse: collapse;
            border: 1px ridge green;

            margin-top: 30px;
        }
        tr:hover{
            background-color: bisque;
        }
        th{
            padding:10px;

            color:white;

            background-color: navy;

            font-size: 18px;
        }
        td{
            padding: 2px;
        }

        caption{
            font-size: 30px;
            font-weight: bold;
            color:darkmagenta;

            padding: 10px;
        }
        td:nth-child(2){
            text-align: left;
            width:40%;
            padding-left: 10px;

            cursor: pointer;
        }

        a, a:link,a:visited {
            text-decoration: none;
            color:black;
        }
        #regBtn{
            width:150px;
            height:40px;
            margin-top: 10px;
            float:right;

            border:0;

            font-size: 18px;
            font-weight: bold;
            font-family: D2Coding;

            color:white;
            background-color: red;

            cursor:pointer;
        }

        #regBtn::after{
            content:'';
            display:block;
            clear:both;
        }

        /* 페이지네이션*/

        #pagination{
            width:100%;
            margin: 0 auto;
            /* border: 1px solid red; */
        }
        #pagination ul li{   
            float:left;

            width:30px;
            height: 30px;
            margin-top:20px;

            list-style:none;
            text-align:center;
            line-height:30px;
                
        }
        #pagination ul li.prev,#pagination ul li.next{
            width:70px;

            font-weight: bold;
        }

        .currPage{
            color:white;
            background-color: green;
        }

    </style>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>
    <script>
        $(function(){
            console.clear();

            $('#regBtn').on('click',function(){
                location.href="/board/new?currPage=${pageMaker.cri.currPage}";
            }); //.onclick

        })
    </script>
    
    <script>
    	var result="${param.result}";
    	if(result != null && result.length>0){
    		alert(result);
    	}
        
    </script>
</head>
<body>
    <h2>/WEB-INF/views/board/list.jsp</h2>
    <hr>

    
    <div id="wrapper">

        <button type="button" id="regBtn">REGISTER</button>
            <table border="1">
                <caption>tbl_board</caption>
                <thead>
                    <tr>
                        <th>bno</th>
                        <th>title</th>
                        <th>writer</th>
                        <th>insertTs</th>
                        <th>updateTs</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="board" items="${list}">
                        <tr>
                            <td>${board.bno}</td>
                            <td><a href="/board/get?bno=${board.bno}&currPage=${pageMaker.cri.currPage}">${board.title}</a></td>
                            <td>${board.writer}</td>
                            <td><fmt:formatDate pattern="yyy/MM/dd HH:mm:ss" value="${board.insertTs}"></fmt:formatDate></td>
                            <td><fmt:formatDate pattern="yyy/MM/dd HH:mm:ss" value="${board.updateTs}"></fmt:formatDate></td>
                        </tr>
                    </c:forEach>

                </tbody>
                <tfoot></tfoot>
        
            </table>

            <div id="pagination">
                <ul>
                    <c:if test="${pageMaker.prev}">
                    <li class="prev"><a href="/board/list?currPage=${pageMaker.startPage - 1}">Prev</a></li>
                    </c:if>

                    <c:forEach var="pageNum" begin="${pageMaker.startPage}" end="${pageMaker.endPage}" >
                    <li class="${pageMaker.cri.currPage==pageNum?'currPage':''}">
                        <a href="/board/list?currPage=${pageNum}">${pageNum}</a></li>
                    </c:forEach>

                    <c:if test="${pageMaker.next}">
                    <li class="next"><a href="/board/list?currPage=${pageMaker.endPage + 1}">Next</a></li>
                    </c:if>
                </ul>
            </div>


    </div>
    
</body>
</html>
