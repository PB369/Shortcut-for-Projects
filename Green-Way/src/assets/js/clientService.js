const getCoins = () => {
  return fetch(`https://economia.awesomeapi.com.br/json/available/uniq`)
  .then(response => {
    if(response.ok) return response.json()
    throw new Error('Não foi possível listar as moedas.')
  })
}

const getPrice = (moeda1, moeda2, campoM2Ref) => {
  return fetch(`https://economia.awesomeapi.com.br/last/${moeda1}-${moeda2}`)
  .then(response => {
    if(response.ok) return response.json()
    campoM2Ref.current.value = ""
    alert('Valor cambial indisponível.')
    return "Não foi possível capturar o preço da moeda."
  })
}

const changePrice = (moeda1, moeda2, campoM2) => {
  return fetch(`https://economia.awesomeapi.com.br/last/${moeda1}-${moeda2}`)
  .then(response => {
    if(response.ok) return response.json()
    campoM2.value = ""
    alert('Valor cambial indisponível.')
    throw new Error('Não foi possível capturar o preço da moeda.')
  })
}

export const clientService = {
  getCoins,
  getPrice,
  changePrice
}