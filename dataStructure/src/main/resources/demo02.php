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
?>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>mysql练习</title>
    <style>
        #myTable {
            margin-left: 300px;
            text-align: center;
        }
        #next {
            margin-left: 500px;
        }
    </style>
</head>
<body>
<div id='app'>
    <table border="1" cellspacing="0" id="myTable">
        <tr>
            <th>number</th>
            <th>name</th>
            <th>gender</th>
            <th>majoring</th>
            <th>photo</th>
            <th>experience</th>
            <th>interest</th>
            <th>speciality</th>
        </tr>
        <tr>
            <td>{{tabelData[index].number}}</td>
            <td>{{tabelData[index].name}}</td>
            <td>{{tabelData[index].gender}}</td>
            <td>{{tabelData[index].majoring}}</td>
            <td>{{tabelData[index].photo}}</td>
            <td>{{tabelData[index].experience}}</td>
            <td>{{tabelData[index].interest}}</td>
            <td>{{tabelData[index].speciality}}</td>
        </tr>
    </table>
    <br>
    <button id="next" @click="clickNext">下一个</button>
</div>

<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.js"></script>
<script>
    //允许使用开发者工具
    Vue.config.devtools = true;
    Vue.config.productionTip = false;
    new Vue({
        el: '#app',
        data: {
            index : 0,
            tabelData: [
                {
                    number : '20211001111',
                    name: '我',
                    gender: '男',
                    majoring: '电信',
                    photo: '',
                    experience: '无',
                    interest: '打球',
                    speciality: '无'
                },
                {
                    number : '202124241',
                    name: '你',
                    gender: '男',
                    majoring: '电信',
                    photo: '',
                    experience: '无',
                    interest: '睡觉',
                    speciality: '无'
                }
            ]
        },
        methods: {
            clickNext(){
                if (this.index === 1) this.index = 0
                else this.index = 1;
            }
        }

    })

</script>

</body>
</html>
