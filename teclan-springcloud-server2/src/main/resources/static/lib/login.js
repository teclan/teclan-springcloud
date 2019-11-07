


function register(){
  $.globalMessenger().post({
     message: "未开放!",
      hideAfter: 3,
      type: 'info'
  });

};


function login(id,password){

     var result;

     var handleSuccess = function(data){
            if(data !== undefined) {
                    try {

                       if(data.code==200){

                        //showMessage(data.message);

                        // 登录成功后，后端会返回认证之前的请求url,如果是直接登录，则返回 null
                        var redirectUrl = data.redirectUrl;

                        if(redirectUrl != null){ // 跳转到认证之前的地址
                            window.location.href=redirectUrl;
                        }else{// 跳到默认页面
                            window.location.href=ROOT_URL+"/home.html";
                        }

                        }else{
                           showMessage(data.message);
                        }


                    } catch(e) {
                        alert("error!"+e);
                        return false;
                    }
           }
      };

 	 var handleFailure = function(o){
 	 };

    var json = '{"account":"'+id+'","password":"'+password+'"}';

     $.ajaxSettings.async = false;

 	sync('POST',BASE_URL+'/auth',json,handleSuccess,handleFailure);

};


function logout(){

     var handleSuccess = function(data){
     };

 	 var handleFailure = function(o){
 	 };

    var json = '{"account":"'+localStorage.getItem("USER")+'"}';
 	sync('POST',BASE_URL+'/logout.do',json,handleSuccess,handleFailure);

};