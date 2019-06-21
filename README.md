# Restaurant
点餐系统

微信小程序界面是在别人的代码基础上改的。

**4.1管理员模块设计**

**4.1.1 管理员登录功能**

（1）管理员登录功能描述与实现

管理在进行管操作前必须进行登录否则不能使用，具体实现方式是通过用户输入的用户名通过selectByid方法在数据库查询并返回Restaurant类，并与用户输入的密码和返回类中的密码进行比较，如果相同则登录成功，并将用户的数据存储在redis中，并给redis设定时间限制，如果密码不相同则会通model的addAttribute方法回传一个erro属性使其显示为用户名或密码错误。

（2）管理员登录界面设计

管理员通过点击主界面右上角的登录按钮，会出现登录弹窗。管理员在弹窗上输入登录信息。

管理员登录界面如图4-1所示。

![img](https://github.com/yueguoyu/Restaurant/blob/master/image/p1.png) 

图4-1 管理员登录后台主页面图

 

**4.1.2管理员用户注册功能**

（1）管理员用户注册功能描述与实现

管理员用户注册功能主要作用是，管理员用户的信息注册，当管理员点击注册按钮时会触发注册按钮绑定的TGDialogS方法，使得注册页面弹出。然后用户在注册页面上输入用户信息。管理用户注册功能具体实现方法是通过前端页面的form表单将数据提交到后台，在用户将数据提交到数据库前，需要先在数据库中查询用户是否注册过，如果没有则上传数据到数据库中并跳转到主页，否则直接跳转主页。用户在注册时需要上传头像，由于数据库不能直接的存储图片，所以需要先将头像图片上传到阿里云的oss对象存储上，然后将获取到的图片地址和注册信息一起添加到数据库中。

（2）管理员用户注册界面设计

餐厅管理用户通过点击管理首页右上角的注册按钮，得到管理员用户注册页面。然后在管理员用户注册页面进行信息的注册，当点击注册按钮时，表单数据会通过post请求上传到后台。

餐厅管理员用户注册界面如图4-2所示。

![img](https://github.com/yueguoyu/Restaurant/blob/master/image/p2.png) 

图4-2 餐厅管理员用户注册页图

**4.1.3管理员管理界面**

当用户登录后会直接跳转到管理员管理界面，管理员管理界面的分布主要是右上角显示登录用户的头像，以及页面顶部的导航栏。由于页面使用的是比较流行的前端框架Bootstrap。所以页面比较美观大方。

登录后台后管理界面如图4-3所示。

![img](https://github.com/yueguoyu/Restaurant/blob/master/image/p3.png) 

图4-3 餐厅管理员管理界面图

 

**4.1.4菜单管理功能**

菜单管理功能包括菜单的展示功能，以及增加菜单功能，菜单的修改功能，菜肴删除功能。

（1）菜单管理功能描述与实现

菜单展示功能的具体实现是通过使用menuDao接口的selectByrid方法请求后台数据库，然后数据库会返回一个存有全部菜单信息的list，接着通过model的addAttribute方法将这个list传给前端页面。前端页面使用thymeleaf的th:each循环迭代方法将list中的信息展示在前端页面。

（2）菜单界面设计

餐厅管理员通过点击管理首页页面顶上导航栏的menu按钮，跳转到展示菜单页面。

菜单界面如图4-4所示。

![img](https://github.com/yueguoyu/Restaurant/blob/master/image/p4.png) 

图4-4 菜单展示界面图

（3）增加菜单功能描述与实现

增加菜单功能的具体实现是通过前端页面的form表单将数据上传到后台，由于上传菜单时需要上传菜肴的图片，而数据库中不能直接存储图片，所以使用了阿里云的对象存储OSS功能，先将图片上传到阿里云的对象存储OSS中，然后再将获取到的图片的地址和添加的菜肴信息一起通过menuDao接口的addMenu方法上传到数据库中。

（4）增加菜单的界面设计

餐厅管理员在登录后进入首页，可以通过首页顶部的导航栏的menu下拉框选择add按钮。当餐厅管理员点击这个按钮时，页面会跳转至菜肴增加页面。餐厅管理者可以在菜肴增加页面填写需要增加的菜肴信息，当菜肴信息填写完成时则点击提交按钮进行提交。

增加菜单界面如图4-5所示。 ![img](https://github.com/yueguoyu/Restaurant/blob/master/image/p5.png)

图4-5 增加菜单界面图

（5）菜单修改功能描述与实现

菜单的修改功能的具体实现是，通过前端页面的form表单将需要修改的数据通过post方法上传到后台。在上传过程中后台需要检测管理者是否输入了需要修改的菜名，如果用户没有输入菜名唯恐或者数据库中没有，前端页面会提示请输入菜名。并且在上传前还会通过hashOperations获取是否有用户登录数据，如果数据为空则返回请登录提示信息，如果数据不为空则通过menuDao接口的updateMenu方法对数据进行更改。当需要更改菜肴的图片时，则是先要将图片上传到阿里云的OSS服务上，获取图片的新地址然后将数据更改。

（6）增加菜单的界面设计

餐厅管理员在登录后，通过点击首页顶部导航栏的下拉菜单menu按钮下all按钮跳转到菜单展示页面。在菜单展示页面中有每个菜下面都有修改按钮，点击修改按钮后就会弹出修改菜肴弹窗。管理员可以直接在修改菜肴的弹窗里进行数据输入。

修改菜单弹窗如图4-6所示。

![img](https://github.com/yueguoyu/Restaurant/blob/master/image/p6.png) 

图4-6 修改菜单的弹窗图

（7）菜肴删除功能描述与实现

菜单的删除功能的具体实现是，在菜单的展示时使用thymeleaf的th:value方法直接获取到这一行显示的菜名放在input标签中，并且将input标签隐藏。然后将数据用form表单通过get方法传给后台，后台在获取数据后先通过hashOperations的hasKey方法判定用户是否登录，如果有登录信息则通过menuDao接口的deleMenuByname方法将菜肴删除，当没有登录信息时直接显示请登录。

**4.1.5 订单管理功能**

订单管理功能包括查询全部订单功能，以及查询实时订单功能和根据订单号查询订单功能。

（1）查询全部订单功能描述与实现

查询全部订单功能的具体实现是通过tOrderMapper接口的selectAll方法获取数据库中的全部订单信息，由于订单信息在数据库存储时是一个菜一条数据，所以在返回数据时需要将相同订单号的数据整合。于时将返回的数据放在一个set中将订单号相同的订单进行去重整合。然后再将set进行遍历将数据放在lsit中，并将生成的list按照时间进行排序，最后在页面通过thymeleaf的th:each循环迭代方法显示。

（2）查询全部订单界面设计

餐厅管理员在登录后，通过点击首页顶上导航栏的下拉菜单Order中的all按钮，跳转到全部订单显示界面。

全部订单显示页面如图4-7所示。

![img](https://github.com/yueguoyu/Restaurant/blob/master/image/p7.png) 

图4-7 全部订单显示界面图

（3）实时查询订单功能描述与实现

实时查询订单功能的具体实现是先通过SimpleDateFormat类中的format方法获取固定格式的当前时间的字符串。然后将字符串截掉后5位，通过tOrderMapper接口的selectByTime方法获取数据库中的全部订单信息，selectByTime方法在获取用户数据时是按照时间进行查询，返回的是一个小时内的订单信息。

（4）查询全部订单界面设计

餐厅管理员在登录后，通过点击首页顶上导航栏的下拉菜单Order中的new按钮，跳转到实时订单显示界面。

实时订单显示页面如图4-8所示。

![img](https://github.com/yueguoyu/Restaurant/blob/master/image/p8.png) 

图4-8 实时订单显示界面图

（5）根据订单号查询订单功能描述与实现

根据订单号查询订单功能的具体实现是通过tOrderMapper接口的selectByNumber方法获取数据库指定订单号的订单数据。然后将返回的数据放在set中，将订单号相同的数据整合到一个类中。接着遍历set将数据放在新的list中并对list按照时间排序。最后在前端页面通过thymeleaf的th:each循环迭代方法显示。

（6）根据订单号查询订单界面设计

餐厅管理员在登录后，通过在首页顶部的搜索框直接输入订单号直接进行订单查询。

根据订单号查询订单显示页面如图4-9所示。

![img](https://github.com/yueguoyu/Restaurant/blob/master/image/p9.png) 

图4-9 根据订单号查询订单显示界面图

**4.2微信小程序点餐模块设计**

**4.2.1 微信小程序点餐首页**

用户通过微信扫一扫扫描小程序二维码，访问到微信小程序首页，在用户访问到微信小程序首页时，微信小程序通过onLaunch的 wx.request方法直接将小程序中获取到用户的code通过post方法传输到后台，后台通过wxLogin方法获取code并请求微信的api获取到用户的唯一标识openid，并将用户的openid存储在redis的openid表中。

微信小程序首页页面如图4-10所示。

![img](https://github.com/yueguoyu/Restaurant/blob/master/image/p10.png)

图4-10 微信小程序首页图

**4.2.2 微信小程序菜单展示模块**

（1）微信小程序选餐功能描述与实现

用户可以通过加号进行点餐，并且可以选择忌口以及口味等。具体实现是用户点击首页的开始点餐按钮后页面跳转到点餐页面，当跳转到点餐页面后会触发点餐页面的js方法里的onLoad里的wx.request方法，它会通过get方法向后台发送一个请求，然后后台会返回全部的菜肴数据并将这些数据全部显示在页面上。当用户要进行菜肴的选择时，需要通过点击页面中的加号进行菜肴的选取。当用户点击加号时会触发弹窗弹，点餐用户可以在弹窗上进行口味以及忌口的选择。用户点击忌口和口味以及速度的按钮时会触发chooseSE方法，在方法中会获取选择的内容。

（2）微信小程序选餐功能界面设计

用户通过点击微信小程序的首页的开始点餐按钮，页面跳转到菜单展示页面。然后用户可以通过点击界面中的加号进行菜肴的选取，在点击加号后会出现弹窗，用户可以在弹窗中选择忌口以及口味和速度，在选择完成后点击弹窗中的加入购物车按钮将选择的菜肴加入购物车。

微信小程序菜单展示模块页面如图4-11所示。

![img](https://github.com/yueguoyu/Restaurant/blob/master/image/p11.png)

图4-11 微信小程序菜单展示页面图

微信小程序点击加号后弹窗如图4-12所示。

![img](https://github.com/yueguoyu/Restaurant/blob/master/image/p12.png)

图4-12 微信小程序点击加号后弹窗图

 

**4.2.3微信小程序购物车模块**

（1）微信小程序购物车功能描述与实现

微信小程序购物车模块的具体实现是，当用户选好餐后点击加入购物车按钮，此时触发了按钮绑定的addToCart方法。addToCart方法将数据放到一个数组中。当用户点击页面中的购物车图标后，购物车弹窗会显示用户选取的菜肴信息。购物车界面内的加号和减号分别绑定了addNumber方法和decNumber方法。addNumber方法可以增加数量，而decNumber方法则可以减少数量。界面中清空购物车按钮则绑定 clearCartList方法，在clearCartList方法中将showCart属性设置为false使得购物车弹窗隐藏并将购物车数据清除。

（2）微信小程序购物车功能界面设计

用户在选择菜肴完成后点击加入购物车按钮，购物车弹窗显示。弹窗中的加号和减号分别可以增加选取菜肴的数量和减少选取菜肴的数量。弹窗中点击清空购物车图标清除购物车中的菜肴信息。

微信小程序购物车弹窗如图4-13所示。

![img](https://github.com/yueguoyu/Restaurant/blob/master/image/p13.png)

图4-13 购物车弹窗图

**4.2.4微信小程序订单详情模块**

（1）微信小程序订单详情模块功能描述与实现

微信小程序订单详细模块，主要功能是展示用户的选取的菜肴以及用户需要添加的备注。主要功能实现是去支付按钮绑定了 gopay方法，当点击按钮时gopay方法将数据通过post方法将数据传输到后台，后台会返回订单信息。gopay方法会将返回的数据存在微信小程序的缓存中。

（2）微信小程序订单详情界面设计

用户点完餐后点击选好了按钮，页面跳转到订单详情。订单详情页面主要希显示的是用户选取的菜肴以及菜肴的数量和单价还有总共的价钱。用户可以在备注的输入框中添加备注。

微信小程序订单详情页面如图4-14所示。

![img](https://github.com/yueguoyu/Restaurant/blob/master/image/p14.png)

图4-14 微信小程序订单图

**4.2.5微信小程序取单模块**

（1）微信小程序取单模块功能描述与实现

微信小程序取单模块，主要功能是展示用户的订单号以及所需的总钱数以及下单时间。主要功能的实现是当监听到页面加载时微信小程序会触发onLoad方法，在onLoad的方法中使用getStorageSync方法获取微信小程序存储在缓存中订单数据，并将数据信息展现在页面上。

（2）微信小程序取单模块界面设计

用户在订单详情页面添加备注后点击去支付按钮，页面显示取单号和订单详情信息，以及用户下单时的时间。

微信小程序订单详情页面如图4-15所示。

![img](https://github.com/yueguoyu/Restaurant/blob/master/image/p15.png) 

图4-15 微信小程序取单页面图

**4.2.6智能推荐模块**

智能推荐模块主要是为用户推荐,与用户以往点餐数量最多的菜肴相似度比较高的菜肴。主要功能实现是将用户的订单信息存储在redis中，将使用协同过滤算法对redis中存储的菜肴订单信息以及用户的信息进行相似的计算，然后将计算后的值存储到redis中。

在实现智能推荐模块时，在程序启动时在redis中创建mean表，并在redis中为每个菜都创建一个以菜名为key的hash表。在程序中并使用了一个定时方法，在每天的凌晨3点对菜肴进行相似度计算。然后将数值存在以菜肴名为key以其他菜名为hashkey的值为相似度的hash表。在为用户推荐菜时，推荐与用户以往点餐数量最多的菜肴相似度最高的多个菜肴。
