var express = require('express');
var router = express.Router();
var service=require("./BlogService");


router.post("/addc",service.addC)
router.get("/listc",service.listC)
router.delete("/deleteC/:id",service.deleteC)


module.exports = router;