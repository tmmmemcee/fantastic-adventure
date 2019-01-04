/*  */
function clearErrors() {
  for (var loopCounter = 0;
    loopCounter < document.forms["contactUs"].elements.length;
    loopCounter++) {
    if (document.forms["contactUs"].elements[loopCounter]
     .parentElement.className.indexOf("has-") != -1) {
        document.forms["contactUs"].elements[loopCounter].parentElement.className = "form-group";
    }
  }
}
/* ensures that required inputs are not empty*/
function validateForm() {
  clearErrors();
  var name = document.forms["contactUs"]["name"].value;
  var email = document.forms["contactUs"]["email"].value;
  var phone = document.forms["contactUs"]["phone"].value;
  if (name == "") {
    alert("You must enter your name")
    document.forms["contactUs"]["name"].parentElement.className = "form-group has-error";
    document.forms["contactUs"]["name"].focus();
    return false
  }
  if (email == "") {
    alert("You must enter your email address")
    document.forms["contactUs"]["email"].parentElement.className = "form-group has-error";
    document.forms["contactUs"]["email"].focus();
    return false
  }
  if (phone == "") {
    alert("You must enter a phone number")
    document.forms["contactUs"]["phone"].parentElement.className = "form-group has-error";
    document.forms["contactUs"]["phone"].focus();
    return false
  }
  document.getElementById("submitSuccess").style.display = "block";
  return false;
}
