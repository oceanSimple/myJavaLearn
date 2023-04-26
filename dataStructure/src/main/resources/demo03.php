<?php
/* Connect to a MySQL server 连接数据库服务器 */

$link = mysqli_connect(
    'localhost',    /* The host to connect to 连接MySQL地址 */
    'root',         /* The user to connect as 连接MySQL用户名 */
    '111111',       /* The password to use 连接MySQL密码 */
    'mydb');    /* The default database to query 连接数据库名称*/

if (!$link)
{
    printf("Can't connect to MySQL Server. Errorcode: %s ", mysqli_connect_error());
    exit;
}
else
{
    echo ('ok')."<br/>";   /* Close the connection 关闭连接*/
}
$result = mysqli_query($link,"SELECT number,name,gender,majoring,photo,experience,interest,speciality from class_notes")
or die("<br>查询表students失败: " . mysqli_error());
error_reporting(0);
$row=mysqli_fetch_row($result);  //读取结果集中的一行
?>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>mysql练习</title>
</head>
<body>
<div id='app'>
    <h1>$row[0]</h1>
    <h1>$row[1]</h1>
    <h1>$row[2]</h1>
</div>

</body>
</html>