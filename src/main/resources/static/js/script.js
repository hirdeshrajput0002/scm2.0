console.log("Script loaded")

//=========================change theme work==============================
let currentTheme= getTheme();
document.addEventListener("DOMContentLoaded",()=>{
    changeTheme();
});
//Todo:
function changeTheme(){
  //set to web page
 changePageTheme(currentTheme,currentTheme);
  //set the listner to change theme button
 const changeThemeButton=document.querySelector("#theme_change_button");
 const oldTheme=currentTheme
 changeThemeButton.addEventListener("click",(event) =>{ 
    console.log("change theme button clicked");
    document.querySelector('html').classList.remove(currentTheme);
    if (currentTheme =="dark") {
        // theme ko light
        currentTheme="light"
    }
    else{
        //theme ko dark
        currentTheme="dark"
    }
   changePageTheme(currentTheme,oldTheme);
 });
}
//set theme to local storage
function setTheme(theme){
    localStorage.setItem("theme",theme);
}
//get theme to local storage
function getTheme(){
    let theme= localStorage.getItem("theme");
    if(theme) return theme;
    else return "light";
}
//change current page theme
function changePageTheme(theme,oldTheme){
     //local storage mai update karenge
     setTheme(currentTheme);
     //remove the current theme
     document.querySelector("html").classList.remove(oldTheme);
     //set the currant theme
     document.querySelector("html").classList.add(theme);
     //change the text of button 
     document.querySelector("#theme_change_button").querySelector("span").textContent = theme == "light" ? "dark" : "light";
    
}
//===================end change theme work=================================