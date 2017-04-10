# 抽奖项目。独立专题
* 建立新抽奖专题
* 在com.maqiao.was.luckActivity.web.luckProject中建立 AbstractActivityProject抽象类的实类
* 
* 只通过ajax得到会员状态 和 进行抽奖后的结果
* 抽奖结果分为：库币(可发送站内信)、实物(发送中奖邮件)。
* 抽奖结果可发中奖邮件，可向中奖会员与管理员邮箱发送中奖信息
* 抽奖资格与抽奖项目与邮件设置等全部使用json进行管理
* 一抽奖项目的建立：
* 通过具体项目中的activityDB.json文件得到抽奖项目的设置
<code><pre>
{
    "targetid": 7,	//编号(唯一)
    "projectName": "registrationaward",	//关键字(唯一)
    "title": "2017鸡年大吉抽奖专题",	//项目名称
    "sortBase": 0,	//基础总数
    "showListMax": 20,	//历史记录数量
    "dateStart": "2017-03-01",	//项目开始日期
    "dateEnd": "2017-04-30",	//项目结束日期
    "rndAllow": false,	//是否需要历史记录随机中奖信息
    "rndListBeforeDay": 2,	//随机中奖信息的中奖日期在当前日期的n天之内
    "msgCoinCode": "2017jnqb",	//中奖库币发送站内信需要的关键字
    "msgGoodsCode": "",	//中奖实物发送站内信需要的关键字
    "overflowTimes": 0,	//有资格的会员有n次溢出次数
    "openSubCount1":false,	//是否需要计算有多少次下级会员(用于此会员的发展下级会员)状态：1
    "openSubCount2":false,	//是否需要计算有多少次下级会员(用于此会员的发展下级会员)状态：2
    "openProjectAmount":false,	//是否计算项目中奖人数总数
    "itemList": [	//中奖项目的设置
        {
            "i": 0,	//编号
            "title_level": "一等奖",	//等级
            "title": "一场活动策划方案",	//名称
            "title_1": "一场活动策划方案",	//小名称
            "angle": 15, 	//概率
            "angleCoin": 0	//中奖所得库币
        },
        {
            "i": 1,
            "title_level": "一等奖",
            "title": "300库币",
            "title_1": "300库币",
            "angle": 10,
            "angleCoin": 300
        }
    ],
    "qualifications": {//会员资格，按照会员状态得到此会员有多少次抽奖机会，机会不会累加，只有一个基础值。例：单品通(活动期注册/非活动期注册)
        "unLogin": 0,
        "personalBetween": 0,
        "personalOverranging": 0,
        "memberIndividualBetween": 1,
        "memberIndividualOverranging": 0,
        "memberFreeBetween": 1,
        "memberFreeOverranging": 0,
        "memberDptBetween": 1,
        "memberDptOverranging": 0,
        "memberHytBetween": 1,
        "memberHytOverranging": 0
    },
    "emailService": {	//设置中奖后向管理员发送的邮件，包括管理员邮箱地址与标题内容。其中{argN}分别在项目类的抽象方法中设置
        "address": [
            "hezhao@99114.com",
            "cptg@99114.com"
        ],
        "emailTitle": "抽奖项目[{arg0}]会员[{arg1}]抽中[{arg2}]",
        "emailContent":"{arg0}--{arg1}--{arg2}"
    },
    "emailMember": {	//设置中奖后向中奖会员发送的邮件，同向管理员发送邮件相通过设置
        "emailTitle": "",
        "emailContent": ""
    }
}
</pre></code>
* 输出url
* 抽奖项目: 
* /luckactivity/registrationaward/siteid/11401
* /luckactivity/vibrationluck/index.html
* 抽奖:
* /luckactivityJson/activityStateJson/7	// 会员状态{/targetid}
* /luckactivityJson/activityCashJson/7	// 进行抽奖，并得到会员状态{/targetid}
* 抽奖结果:
* /luckactivityHistroy/getActivityHistroyResult/7/10/1	// 查看某个项目的中奖信息{/targetid/pagesize/page}
* 系统操作:
* /luckactivityWork/resetAll	//重置抽奖系统，清空缓存，从json依次读取各个抽奖项目
* 抽奖检测:
* /luckactivityTest/getRndResult/7/1000	// 对某个抽奖项目进行随机次数检测{/targetid/num}








