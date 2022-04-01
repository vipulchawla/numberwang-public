const express = require('express')

const port = 8080

const randInt = (min, max) => {
  return Math.floor(
    (Math.random() * (max - min)) + min
  )
}

const makeServer = () => {
  const app = express()
  const numberwang = randInt(0, 10)
  const win = "True"
  const fail = "False"
  app.get('/guess', (req, res) => {
    const guess = parseInt(req.query.number)
    if (numberwang === guess) {
      res.send(win)
    } else {
      res.send(fail)
    }
  })
  return app
}

const main = () => {
  const app = makeServer()
  app.listen(port, () => {
    console.log(`Server started on ${port}`)
  })
}

module.exports = {
  makeServer
}

if (require.main === module) {
  main()
}
