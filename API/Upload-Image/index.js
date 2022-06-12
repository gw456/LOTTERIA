const express = require("express");
const Multer = require("multer");
const { Storage } = require("@google-cloud/storage");

const app = express();

// firebase init
var firebaseConfig = {
    apiKey: "AIzaSyA8xoLENQc8zdGKs2iKZjo9vnJpUH421DQ",
    authDomain: "keen-enigma-345002",
    databaseURL: "https://accounts.google.com/o/oauth2/auth",
    projectId: "keen-enigma-345002",
    storageBucket: "keen-enigma-345002.appspot.com",
    messagingSenderId: "1072285161278",
    appId: "1:1072285161278:android:1f3904c1f9bb4a28d63e6f"
  };


const storage = new Storage({
  projectId: "keen-enigma-345002",
  keyFilename: ".\serviceAccountKey.json",
});
const bucket = storage.bucket("keen-enigma-345002.appspot.com");

// multer
const multer = Multer({
  storage: Multer.memoryStorage(),
  //limits: {
    fileSize: 5 * 2048 * 2048, 
  //},
});

// upload image to storage function
const uploadImageToStorage = (file) => {
  return new Promise((resolve, reject) => {
    if (!file) {
      reject("No image file");
    }
    let newFileName = `${file.originalname}_${Date.now()}`;

    let fileUpload = bucket.file(newFileName);

    const blobStream = fileUpload.createWriteStream({
      metadata: {
        contentType: file.mimetype,
      },
    });

    blobStream.on("error", (error) => {
      reject(error);
    });

    blobStream.on("finish", () => {
    
      const url = `https://storage.googleapis.com/${bucket.name}/${fileUpload.name}`;
      resolve(url);
    });

    blobStream.end(file.buffer);
  });
};

// Routes
app.post("/upload", multer.single("file"), (req, res) => {
  let file = req.file;
  if (file) {
    uploadImageToStorage(file)
      .then((url) => {
        return res.status(200).send({
          image: url,
        });
      })
      .catch((error) => {
        return res.status(500).send({
          error: error,
        });
      });
  } else {
    return res.status(422).send({
      error: "file is required",
    });
  }
});

// PORT
const port = 8080;

// Starting a server
app.listen(port, () => {
  console.log(`app is running at ${port}`);
});