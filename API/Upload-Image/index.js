var firebaseConfig = {
    apiKey: "AIzaSyA8xoLENQc8zdGKs2iKZjo9vnJpUH421DQ",
    authDomain: "keen-enigma-345002",
    databaseURL: "https://accounts.google.com/o/oauth2/auth",
    projectId: "keen-enigma-345002",
    storageBucket: "keen-enigma-345002.appspot.com",
    messagingSenderId: "1072285161278",
    appId: "1:1072285161278:android:1f3904c1f9bb4a28d63e6f"
  };
  // Initialize Firebase
  firebase.initializeApp(firebaseConfig);
  console.log(firebase);

  function uploadImage() {
    const ref = firebase.storage().ref();
    const file = document.querySelector("#photo").files[0];
    const name = +new Date() + "-" + file.name;
    const metadata = {
      contentType: file.type
    };
    const task = ref.child(name).put(file, metadata);
    task
      .then(snapshot => snapshot.ref.getDownloadURL())
      .then(url => {
        console.log(url);
        document.querySelector("#image").src = url;
      })
      .catch(console.error);
  }

const video = document.getElementById('video');
const canvas = document.getElementById('canvas');
const snap = document.getElementById("snap");
const errorMsgElement = document.querySelector('span#errorMsg');

const constraints = {
audio: false,
video: {
  width: 400, height: 400
}
};

// Access webcam
async function init() {
try {
  const stream = await navigator.mediaDevices.getUserMedia(constraints);
  handleSuccess(stream);
} catch (e) {
  errorMsgElement.innerHTML = `navigator.getUserMedia error:${e.toString()}`;
}
}

// Success
function handleSuccess(stream) {
window.stream = stream;
video.srcObject = stream;
}

// Load init
init();

// Draw image
var context = canvas.getContext('2d');
snap.addEventListener("click", function() {
  context.drawImage(video, 0, 0, 640, 480);
  var image = new Image();
image.id = "pic";
image.src = canvas.toDataURL();
console.log(image.src)
var button = document.createElement('button')
button.textContent = 'Upload Image'
document.body.appendChild(button)

button.onclick = function() {
  const ref = firebase.storage().ref();
  ref.child(new Date() + '-' + 'base64').putString(image.src, 'data_url').then(function(snapshot) {
  console.log('Uploaded a data_url string!');
  alert("Image Uploaded")
});
}
});