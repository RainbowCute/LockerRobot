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


--- 服务员小樱
1.given：小樱管理1个S号locker，一个M号的PrimaryLockerRobot，一个L号的SuperLockerRobot，每个都有空位，普通客户，待存的S号的包
when：小樱存包
then：返回票据，包存在S号的locker中

2.given：小樱管理1个S号locker，一个M号的PrimaryLockerRobot，一个L号的SuperLockerRobot，每个都有空位，普通客户，待存的M号的包
when：小樱存包
then：返回票据，包存在PrimaryLockerRobot中

3.given：小樱管理1个S号locker，一个M号的PrimaryLockerRobot，一个L号的SuperLockerRobot，每个都有空位，普通客户，待存的L号的包
when：小樱存包
then：返回票据，包存在SuperLockerRobot中

4.given：小樱管理1个S号locker，一个M号的PrimaryLockerRobot，一个L号的SuperLockerRobot，S号locker没有空位，普通客户，待存的S号的包
when：小樱存包
then：提示储物柜已满

5.given：小樱管理1个S号locker，一个M号的PrimaryLockerRobot，一个L号的SuperLockerRobot，M号的PrimaryLockerRobot没有空位，普通客户，待存的M号的包
when：小樱存包
then：提示储物柜已满

6.given：小樱管理1个S号locker，一个M号的PrimaryLockerRobot，一个L号的SuperLockerRobot，L号的SuperLockerRobot没有空位，普通客户，待存的L号的包
when：小樱存包
then：提示储物柜已满

7.given：小樱管理1个S号locker，一个M号的PrimaryLockerRobot，一个L号的SuperLockerRobot，普通客户，有效票据
when：小樱取包
then：返回对应的包

8.given：小樱管理1个S号locker，一个M号的PrimaryLockerRobot，一个L号的SuperLockerRobot，普通客户，无效票据
when：小樱取包
then：提示票无效


-- locker 
1.given：3个不同型号locker产生的有效票据
when：locker取包
then：提示票的型号不对

-- primary locker robot
1.given：1个robot，管理的locker和自己的型号不匹配
when：robot存包
then：提示型号不对


--- LockerRobotManager
1.given：LockerRobotManager管理1个S号locker，一个M号的PrimaryLockerRobot，一个L号的SuperLockerRobot，每个都有空位，VIP客户，待存的S号的包
when：LockerRobotManager存包
then：返回票据，包存在S号的locker中

2.given：LockerRobotManager管理1个S号locker，一个M号的PrimaryLockerRobot，一个L号的SuperLockerRobot，每个都有空位，VIP客户，待存的M号的包
when：LockerRobotManager存包
then：返回票据，包存在PrimaryLockerRobot中

3.given：LockerRobotManager管理1个S号locker，一个M号的PrimaryLockerRobot，一个L号的SuperLockerRobot，每个都有空位，VIP客户，待存的L号的包
when：LockerRobotManager存包
then：返回票据，包存在SuperLockerRobot中

4.given：LockerRobotManager管理1个S号locker，一个M号的PrimaryLockerRobot，一个L号的SuperLockerRobot，S号locker没有空位，VIP客户，待存的S号的包
when：LockerRobotManager存包
then：提示储物柜已满

5.given：LockerRobotManager管理1个S号locker，一个M号的PrimaryLockerRobot，一个L号的SuperLockerRobot，M号的PrimaryLockerRobot没有空位，VIP客户，待存的M号的包
when：LockerRobotManager存包
then：提示储物柜已满

6.given：LockerRobotManager管理1个S号locker，一个M号的PrimaryLockerRobot，一个L号的SuperLockerRobot，L号的SuperLockerRobot没有空位，VIP客户，待存的L号的包
when：LockerRobotManager存包
then：提示储物柜已满

7.given：LockerRobotManager管理1个S号locker，一个M号的PrimaryLockerRobot，一个L号的SuperLockerRobot，VIP客户，有效票据
when：LockerRobotManager取包
then：返回对应的包

8.given：LockerRobotManager管理1个S号locker，一个M号的PrimaryLockerRobot，一个L号的SuperLockerRobot，VIP客户，无效票据
when：LockerRobotManager取包
then：提示票无效