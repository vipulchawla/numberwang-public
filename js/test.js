const request = require('supertest')
const { makeServer } = require('./server')

// const srv = makeServer()
let srv
const restartServer = () => {
  srv = makeServer()
}

restartServer()

describe('/guess?number=:guess', () => {
  it('exists', async () => {
    const res = await request(srv).get('/guess?number=3')
    expect(res.status).toBe(200)
  })

  it('restarts', async () => {
    let res
    res = await request(srv).get('/guess?number=3')
    expect(res.status).toBe(200)
    restartServer()
    res = await request(srv).get('/guess?number=3')
    expect(res.status).toBe(200)
  })
})
