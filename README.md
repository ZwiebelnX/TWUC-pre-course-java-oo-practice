# Java oo 练习

做一个热搜排行榜系统

系统角色和功能需求：

* 角色：管理员，普通用户
* 管理员可以：
    * 查看热搜排行榜
    * 添加热搜
    * 添加超级热搜
* 普通用户可以：
    * 查看热搜排行榜
    * 给热搜事件投票
    * 购买热搜
    * 添加热搜

各个功能描述：

* 查看热搜排行榜：显示所有热搜列表，包括热搜排名，热搜描述，热搜的热度，热搜按照热度的高低进行排序
* 添加热搜：添加一个新的热搜（热搜描述作为区分，不区分大小写），添加成功后热搜会被添加到热搜列表中，不会重复添加。
* 给热搜事件投票：用户可以给对应的热搜事件投票，一票等于一热度，一个用户有十票，可以给不同的事件投票，可以一次投任意票数。
* 购买热搜：用户可以选择给热搜排行榜上的某个事件购买特定排名的热搜：
    * 购买热搜是拍卖机制，即用户可以花任意价格购买热搜
        * 如果该排名上的热搜没有人购买，那么用户花任意价格即可买到该位热搜
        * 如果该排名上热搜已被购买，用户需要花高于当前价格的钱即可买到该位热搜，原热搜将会被替换掉（删除）
        * 购买热搜金额为整数，购买排名范围在1到当前热搜数量最后一位
*添加超级热搜： 管理员希望给自己特权，可以添加超级热搜，当一个事件被添加为超级热搜时，用户每给它投一票，它会增加两热度，也就是普通事件的二倍。

### 注意
* 系统交互方式可以采取命令行交互的方式
* 购买热搜的功能比较复杂，如果同学们对系统实现有困难，建议可以将购买热搜功能放到最后完成