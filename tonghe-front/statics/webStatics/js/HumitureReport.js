$(function(){
    /*temandhum为温度数据,esdstatusMan为人员ESD数据,esdstatusMachine为机器ESD数据,boardconfig为看板配置数据*/
    // 异步加载数据-产线状态
    let refreshTime=0;
    var successfn=function(res){
        $(".container>.wrapper").css("display","block");
        $(".container>.loading").css("display","none");
        refreshTime = res.data.boardconfig.refreshTime*1000; /*刷新时间*/
        var humiture=res.data.temandhum; /*温湿度*/
        // console.log("数据请求成功");
        // let humiture=res.data.temandhum.map(function(self,n){
        //     return {
        //         ...self,
        //         esdStatus:true
        //     }
        // });/*温湿度数据*/
        let html="",humitureHtml="", failEsd=[];
        let esdData=[];
        if(res.data.esdstatusMan.length>0&&res.data.esdstatusMan){
             esdData=res.data.esdstatusMan;/*人员esd数据*/
            /*人员esd*/
            esdData.forEach(function(item,index){
                html+=` <li class=${item.fail>0&&"red"}>
                            <p class="name">${item.place}</p>
                            <div class="number" >
                                <div>
                                    <p>${item.ok}</p>
                                    <p>正常</p>
                                </div>
                                <i class="line"></i>
                                <div class="false">
                                    <p  style="color:${item.fail>0?'#f03755':''}">${item.fail}</p>
                                    <p>异常</p>
                                </div>
                            </div>
                            <div class="cover">
                                <div class="position">
                                    <div class="bg">                           
                                    </div>
                                </div>
                            </div>
                        </li>`;
                failEsd.push(item.failEsd?item.failEsd:[]);

            });
            $(".ESDControl>.chart>.area>.list").html(html);
            /*ESD人体实时监控弹框内容开始*/
            let manEsdLi=$(".ESDControl>.chart>.area>.list>li");
            let i=0;
            for(let item of manEsdLi){
                for(let k=0;k<failEsd.length;k++){
                    if(i==k&&failEsd[k].length>0){
                        let manEsdCover="";
                        for(let value of failEsd[k]){
                            manEsdCover+=`<p>人员ID：${value.place}</p><p>工位名称：${value.esdId}</p>`;
                        }
                        $(item).find(".bg").append(manEsdCover);
                    }else if(failEsd[k].length==0){
                    }
                }
                i++;
            }
            /*ESD人体实时监控弹框内容结束*/
            var humanESD=$(".ESDControl>.chart>.area>.list>li")
            for(let item1 of humanESD){
                // if( $(item1).find(".false>p:eq(0)").text()>0){
                //     $(item1).mouseenter(function(){
                //         $(item1).find(".number").css("visibility","hidden");
                //         $(item1).find(".cover").css("visibility","visible");
                //     });
                //     $(item1).mouseleave(function() {
                //         $(item1).find(".cover").css("visibility", "hidden");
                //         $(item1).find(".number").css("visibility","visible");
                //     });
                // }
            }

        }else{
            $(".ESDControl>.chart>.area>.list").html(`<p class="empty" style="width=100%;position:absolute;left:50%;top:50%;transform: translate(-50%,-50%);transform-origin: center;font-size: 1.667rem;text-align: center">抱歉，后台没数据</p>`)
        }

        /*线和机器ESD数据*/
        let esdstatusMachine=res.data.esdstatusMachine;
        for(let i of humiture){
            for(let k of esdData){
              if(i.place==k.place){
                  if(k.fail>0){
                      i.esdStatus=false;
                  }
              }
            }
        }

        let regionElement=$(".humiture>.layout>.region");
        let changeState=function(data){
            if(data=humiture){
                for(let item of regionElement){
                        for(let i=0;i<data.length;i++){
                            if($(item).find(".areaName").text()==data[i].place){
                                $(item).find("li").eq(0).children("span").text(data[i].temperature);
                                $(item).find("li").eq(1).children("span").text(data[i].hamidity);
                                $(item).find("li").eq(0).attr("class",(data[i].temperature>28||data[i].temperature<20)?'red':(data[i].temperature>25||data[i].temperature<18)?'yellow':'')
                                $(item).find("li").eq(1) .attr("class",(data[i].hamidity>80||data[i].hamidity<45)?'red':(data[i].hamidity>75||data[i].hamidity<40)?'yellow':'');
                                if( $(item).find("li").hasClass("red")){
                                    if( $(item).find("li").hasClass("errorBg")){
                                        $(item).removeClass("errorBg");
                                    }else{
                                        $(item).addClass("errorBg");
                                    }
                                }
                            }
                        }
                    }
            }
            if(data==esdstatusMachine){
            }
        };
        changeState(humiture);

        for(let item of regionElement) {
            for(let value of  $(item).find(".light")){
                let faliInfoHtml='';
                for (let i = 0; i < esdstatusMachine.length; i++) {
                    if (($(value).attr("data-lineName") == esdstatusMachine[i].place) && (esdstatusMachine[i].fail > 0)) {
                        $(value).addClass("red");
                        let html=`<div class="hint">
                                <div class="numberHint"><p>报错数</p><p class="failNum">${ esdstatusMachine[i].fail.length=1?'0'+esdstatusMachine[i].fail:esdstatusMachine[i].fail}</p></div>
                                <div class="faliInfo">
                                    <p class="title">${ esdstatusMachine[i].fail.length=1?'0'+esdstatusMachine[i].fail:esdstatusMachine[i].fail }</p>
                                    <div class="wrapper">
                                        <ul>                                    
                                        </ul>
                                    </div>
                                </div>
                            </div>`;
                        $(value).html(html);
                        $(value).find(".numberHint").css("display","block");
                        if( esdstatusMachine[i].failEsd!=null&& esdstatusMachine[i].failEsd.length>0){
                            for(let v of esdstatusMachine[i].failEsd){
                                faliInfoHtml+=`<li>设备ID：${v.esdId}</li><li>设备位置：${v.place}</li>`;
                            }
                        }else{
                            faliInfoHtml+=`<li>报错详情暂未提供</li>`;
                        }

                        $(value).find("ul").html(faliInfoHtml);
                    }

                }

            }
        }

        /*自动刷新页面*/
        let timer=setTimeout(()=>{
            clearTimeout(timer);
            timer=null;
            window.location.reload();
        }, 500000000);
    };
    var errorfn=function(e){
        alert("数据请求失败");
        let html=`<p class="empty" style="width=100%;position:absolute;left:50%;top:50%;transform: translate(-50%,-50%);transform-origin: center;font-size: 1.667rem;text-align: center">请求失败了，☹☹</p>`;
        $(".container>.wrapper").css("display","block");
        $(".container>.loading").css("display","none");
        $(".ESDControl>.chart>.area").html(html);
    };
    /*获取数据*/
    $(".container>.wrapper").css("display","none");
    request('HumitureReport','','GET',successfn,errorfn);

});