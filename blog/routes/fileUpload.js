var uuid = require("uuid");
async function fileupload(req, res, next) {
  // if (req.files === null) {
  //   return res.status(400).json({ msg: "No file uploaded" });
  // }
  const img = req.files.img;
  const fileId = uuid.v4();
  const fileName = fileId + img.name;
  img.mv(`${__dirname}/../public/uploads/${fileName}`, (err) => {
    if (err) {
      console.error(err);
      return res.status(500).send(err);
    }
    res.json({ fileName: fileName, filePath: `/uploads/${fileName}` });
  });
}

module.exports = { fileupload };
