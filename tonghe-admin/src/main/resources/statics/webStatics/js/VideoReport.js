
// $(window).ready(function(){
//
//         var html='';
//         var ulElement=$(".videoWrap>.list");
//         console.log(ulElement);
//         for(var i=1;i<=9;i++){
//             html+=`<li>
//             <div class="left"  style="width:100%;height:100%;">
//                             <div id="divPlugin" class="plugin" style="width:100%;height:100%;" ></div>
//                             <fieldset class="login" style="display: none">
//                                  <legend>登录</legend>
//                                 <table cellpadding="0" cellspacing="3" border="0">
//                                 <tr>
//                                     <td class="tt">IP地址</td>
//                                     <td><input id="loginip" type="text" class="txt" value="10.183.31.237" /></td>
//                                     <td class="tt">端口号</td>
//                                     <td><input id="port" type="text" class="txt" value="80" /></td>
//                                 </tr>
//                                 <tr>
//                                     <td class="tt">用户名</td>
//                                     <td><input id="username" type="text" class="txt" value="admin" /></td>
//                                     <td class="tt">密码</td>
//                                     <td><input id="password" type="password" class="txt" value="123456789p" /></td>
//                                 </tr>
//                                 <tr>
//                                     <td class="tt">设备端口</td>
//                                     <td colspan="2"><input id="deviceport" type="text" class="txt" />（可选参数）</td>
//                                     <td>
//                                         窗口分割数&nbsp;
//                                         <select class="sel2" onchange="changeWndNum(this.value);">
//                                             <option value="1" selected>1x1</option>
//                                             <!--<option value="2">2x2</option>-->
//                                             <!--<option value="3">3x3</option>-->
//                                             <!--<option value="4">4x4</option>-->
//                                         </select>
//                                     </td>
//                                 </tr>
//                                 <tr>
//                                     <td class="tt">RTSP端口</td>
//                                     <td colspan="3"><input id="rtspport" type="text" class="txt" />（可选参数）</td>
//                                 </tr>
//                                 <tr>
//                                     <td colspan="4">
//                                         <input type="button" class="btn" value="登录" onclick="clickLogin();" />
//                                         <input type="button" class="btn" value="退出" onclick="clickLogout();" />
//                                         <input type="button" class="btn2" value="获取基本信息" onclick="clickGetDeviceInfo();" />
//                                     </td>
//                                 </tr>
//                                 <tr>
//                                     <td class="tt">已登录设备</td>
//                                     <td>
//                                         <select id="ip" class="sel" onchange="getChannelInfo();getDevicePort();"></select>
//                                     </td>
//                                     <td class="tt">通道列表</td>
//                                     <td>
//                                         <select id="channels" class="sel"></select>
//                                     </td>
//                                 </tr>
//                             </table>
//                             </fieldset>
//                         </div>
//                         <div class="left" style="display:none">
//                             <fieldset class="preview">
//                                 <legend>预览</legend>
//                                 <table cellpadding="0" cellspacing="3" border="0">
//                                     <tr>
//                                         <td class="tt">码流类型</td>
//                                         <td>
//                                             <select id="streamtype" class="sel">
//                                                 <option value="1">主码流</option>
//                                                 <option value="2">子码流</option>
//                                                 <option value="3">第三码流</option>
//                                                 <option value="4">转码码流</option>
//                                             </select>
//                                         </td>
//                                         <td>
//                                             <input type="button" class="btn" value="开始预览" onclick="clickStartRealPlay();" />
//                                             <input type="button" class="btn" value="停止预览" onclick="clickStopRealPlay();" />
//                                         </td>
//                                     </tr>
//                                     <tr>
//                                         <td class="tt">音量</td>
//                                         <td>
//                                             <input type="text" id="volume" class="txt" value="50" maxlength="3" />&nbsp;<input type="button" class="btn" value="设置" onclick="clickSetVolume();" />（范围：0~100）
//                                         </td>
//                                         <td>
//                                             <input type="button" class="btn" value="打开声音" onclick="clickOpenSound();" />
//                                             <input type="button" class="btn" value="关闭声音" onclick="clickCloseSound();" />
//                                         </td>
//                                     </tr>
//                                     <tr>
//                                         <td class="tt">对讲通道</td>
//                                         <td>
//                                             <select id="audiochannels" class="sel">
//
//                                             </select>
//                                             <input type="button" class="btn" value="获取通道" onclick="clickGetAudioInfo();" />
//                                         </td>
//                                         <td>
//                                             <input type="button" class="btn" value="开始对讲" onclick="clickStartVoiceTalk();" />
//                                             <input type="button" class="btn" value="停止对讲" onclick="clickStopVoiceTalk();" />
//                                         </td>
//                                     </tr>
//                                     <tr>
//                                         <td colspan="3">
//                                             <input type="button" class="btn" value="抓图" onclick="clickCapturePic();" />
//                                             <input type="button" class="btn" value="开始录像" onclick="clickStartRecord('realplay');" />
//                                             <input type="button" class="btn" value="停止录像" onclick="clickStopRecord('realplay');" />
//                                         </td>
//                                     </tr>
//                                     <tr>
//                                         <td colspan="3">
//                                             <input type="button" class="btn2" value="启用电子放大" onclick="clickEnableEZoom();" />
//                                             <input type="button" class="btn2" value="禁用电子放大" onclick="clickDisableEZoom();" />
//                                             <input type="button" class="btn2" value="启用3D放大" onclick="clickEnable3DZoom();" />
//                                             <input type="button" class="btn2" value="禁用3D放大" onclick="clickDisable3DZoom();" />
//                                             <input type="button" class="btn" value="全屏" onclick="clickFullScreen();" />
//                                         </td>
//                                     </tr>
//                                     <tr>
//                                         <td colspan="3">
//                                             分辨率：<input id="resolutionWidth" type="text" class="txt" /> x <input id="resolutionHeight" type="text" class="txt" />
//                                             <input type="button" class="btn" value="设备抓图" onclick="clickDeviceCapturePic();" />
//                                         </td>
//                                     </tr>
//                                 </table>
//                             </fieldset>
//                         </div>
//                       </li>`;
//             // $(item).html(html);
//         }
//     $(".videoWrap>.list").html(html);
//     }
// )