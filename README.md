# LockerRobot
LockerRobot

--- SuperLockerRobot
1.given：SuperLockerRobot管理1个储物柜，储物柜有空位，待存的包
when：SuperLockerRobot存包
then：返回票据

2.given：SuperLockerRobot管理2个储物柜，第1个储物柜有空置率更大，待存的包
when：SuperLockerRobot存包
then：返回票据，并且存在第1个储物柜中

3.given：SuperLockerRobot管理2个储物柜，第2个储物柜空置率更大，待存的包
when：SuperLockerRobot存包
then：返回票据，并且存在第2个储物柜中

4.given：SuperLockerRobot管理2个储物柜，两个储物柜有空位，且空置率一样，待存的包
when：SuperLockerRobot存包
then：返回票据，并且存在第1个储物柜中

5.given：SuperLockerRobot管理2个储物柜，都没有空位，空置率都为0，待存的包
when：SuperLockerRobot存包
then：提示储物柜已满

6.given：SuperLockerRobot管理1个储物柜，有效票据
when：SuperLockerRobot取包
then：返回对应的包

7.given：SuperLockerRobot管理1个储物柜，伪造的票
when：SuperLockerRobot取包
then：提示票无效