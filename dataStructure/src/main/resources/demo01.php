<?php
//mysqli事务操作
//by www.jbxue.com
$sql1 = "update User set ScoreCount = ScoreCount +10 where ID= '123456'";
$sql2 = "update ScoreDetail  set FScore = 300 where ID= '123456'";
$sql3 = "insert into  ScoreDetail ID,Score) values ('123456',60)";

$mysqli = new mysqli('localhost','root','','DB_Lib2Test');
$mysqli->autocommit(false); // 开始事务
$mysqli->query($sql1);
$mysqli->query($sql2);
if (!$mysqli->errno) {
    $mysqli->commit();
    echo 'ok';
} else {
    echo 'err';
    $mysqli->rollback();
}