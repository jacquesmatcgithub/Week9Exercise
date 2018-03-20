<%@include file="head.jsp"%>
<html>
    <body>
    <div class="container-fluid">
        <h2>User Display Exercise - Week 1</h2>
        <h3>Hello there.</h3>
        <br/>
        <table>
            <tr><td>Click <a href="searchUser"><b>here</b></a> to list all users</td></tr>
            <tr><td>or</td></tr>
            <tr><td>
                    <form action="searchLastName" method="GET">
                        Search by Last Name: <input type="text" name="searchTerm" >
                        <input type="submit" name="" value="Search" />
                    </form>
                </td>
            </tr>
        </table>
    </div>
    </body>
</html>