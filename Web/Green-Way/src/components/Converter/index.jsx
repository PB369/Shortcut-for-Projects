import './css/Converter-Desktop.scss'
import './css/Converter-Mobile.scss'
import { clientService } from '../../assets/js/clientService.js'
import { useEffect, useState, useRef } from 'react'
import bigArrows from '../../assets/svg/big-arrows.svg'
import smallArrows from '../../assets/svg/small-arrows.svg'
import copyIcon from '../../assets/svg/copy-icon.svg'
import copiedIcon from '../../assets/svg/copied-icon.svg'

const Converter = () => {
  const inputC1Ref = useRef()
  const inputC2Ref = useRef()
  const currency1Ref = useRef()
  const currency2Ref = useRef()
  const buttonCopyRef = useRef()
  const currencyValueRef = useRef(0)
  const[copyContent, setCopyContent] = useState([])
  const[copiedContent, setCopiedContent] = useState([])
  const[arrowImage, setArrowImage] = useState(null)
  const[coins, setCoins] = useState([])
  const[date, setDate] = useState('Carregando data atual...')
  const[copiedC1, setCopiedC1] = useState(false)
  const[copiedC2, setCopiedC2] = useState(false)
  const[windowWidth, setWindowWidth] = useState(window.innerWidth)

  //Set date:
  useEffect(() => {
    const monthArray = ["jan", "fev", "mar", "abr", "maio", "jun", "jul", "ago", "set", "out", "nov", "dez"]

    const getDate = setInterval(() => {
      const dateObj = new Date()
      const month = monthArray[dateObj.getUTCMonth()]
      const day = String(dateObj.getUTCDate()).padStart(2, '0')
      const hour = String(dateObj.getUTCHours()).padStart(2, '0')
      const minutes = String(dateObj.getUTCMinutes()).padStart(2, '0')

      const dateReturn = `${day} de ${month}, ${hour}:${minutes} UTC`
      setDate(dateReturn)
    }, 1000)
    return () => clearInterval(getDate)
  }, [date])

  //Copy price:
  const handleClickToCopy = (event) => {
    const currency = event.target
    if(currency.id === "1"){
      if(copiedC1 === false){
        navigator.clipboard.writeText(inputC1Ref.current.value)
        currency.style.cursor = 'default' 
        setCopiedC1(true)
      }
      setTimeout(()=>{
        setCopiedC1(false) 
        currency.style.cursor = 'pointer'
      }, 2500)
    }

    if(currency.id === "2"){
      if(copiedC2 === false){
        navigator.clipboard.writeText(inputC2Ref.current.value)
        currency.style.cursor = 'default'
        setCopiedC2(true)
      }
      setTimeout(()=>{
        setCopiedC2(false) 
        currency.style.cursor = 'pointer'
      }, 2500)
    }
  }

  //Get all coins:
  useEffect(() => {
    const currencyOptions = async () => {
      const coinsObj = await clientService.getCoins()
      const coinsGroup = Object.keys(coinsObj).map(coin => ({
        value: coin,
        label: coinsObj[coin]
      }))
      setCoins(coinsGroup)
    }
    currencyOptions()
  }, [])

  //Get exchange rate:
  const exchangeRate = async (event) => {
    const invertCoinsEvent = event
    const currency1 = currency1Ref.current.value
    const currency2 = currency2Ref.current.value

    if(currency1 === currency2) {
      alert('Por favor, escolha moedas diferentes umas das outras.')
      inputC2Ref.current.value = inputC1Ref.current.value
    }
    
    else{
      const data = await clientService.getPrice(currency1, currency2, inputC2Ref)

      if(data === "Não foi possível capturar o preço da moeda.") {
        throw new Error(data)
      }

      let currencyObj = data[currency1 + currency2]
      let currencyValue = parseFloat(currencyObj.ask)
      let inputCurrencyValue = currencyValue * inputC1Ref.current.value
      if(!invertCoinsEvent) {
        inputC2Ref.current.value = inputCurrencyValue.toFixed(2)
        if(!inputC1Ref.current.value){inputC1Ref.current.value = 1}
        if(inputC1Ref.current.value == 0){inputC2Ref.current.value = 0}
      }
      currencyValueRef.current = currencyValue
    }
  }
  
  useEffect(() => {exchangeRate()}, [])

  const handleSelectChange = () => {exchangeRate()}

  //Change price:
  const handleInputC1 = (event) => {
    let currency1Value = event.target.value
    let priceCalc = currency1Value * currencyValueRef.current
    inputC2Ref.current.value = priceCalc.toFixed(2)
  }

  const handleInputC2 = (event) => {
    let currency2Value = event.target.value
    let priceCalc = currency2Value / currencyValueRef.current
    inputC1Ref.current.value = priceCalc.toFixed(2)
  }

  //Watch window width:
  useEffect(()=>{
    const handleWidthResize = () => setWindowWidth(window.innerWidth)
    
    window.addEventListener("resize", handleWidthResize)

    return () => {window.removeEventListener("resize", handleWidthResize)}

  }, [windowWidth])

  //Render icon or text in copy button:
  useEffect(()=>{
    const defineContent = () => {
    if(windowWidth > 1440){
      setCopyContent(["Copiar", "Copiar"])
      setCopiedContent(["Copiado!", "Copiado!"])
    }else {

      setCopyContent(
        [<img src={copyIcon} key={1} alt='Ícone de Copiar' id='1'/>,<img src={copyIcon} key={2}  alt='Ícone de Copiar' id='2'/>]
      )
      setCopiedContent(
        [<img src={copiedIcon} key={1} alt='Ícone de Ação Realizada' id='1'/>,<img src={copiedIcon} key={2}  alt='Ícone de Copiar' id='2'/>]
        )
    }
  }
  defineContent()
  }, [windowWidth])

  //Render big or small arrows:
  useEffect(()=>{
    const defineContent = () => {
    if(windowWidth > 1440){
      setArrowImage(<img src={bigArrows} alt='Setas bidirecionais' id='arrows' draggable={false} onClick={invertCoins}/>)
    }else {
      setArrowImage(<img src={smallArrows} alt='Setas bidirecionais' id='arrows' draggable={false}  onClick={invertCoins}/>)
    }
  }
  defineContent()
  }, [windowWidth])

   //Invert coins:
   const invertCoins = (event) => {
    const selectCurrency1Value = currency1Ref.current.value
    const selectCurrency2Value = currency2Ref.current.value
    const inputCurrency1Value = inputC1Ref.current.value
    const inputCurrency2Value = inputC2Ref.current.value

    currency1Ref.current.value = selectCurrency2Value
    currency2Ref.current.value = selectCurrency1Value
    inputC1Ref.current.value = inputCurrency2Value
    inputC2Ref.current.value = inputCurrency1Value

    exchangeRate(event)
  }

  return (
    <div className='converterCard'>
      <div id="converterContent">
        <p id='date'>{date}</p>
        <div id="converterArea">
            <div className="currency"  id='currency1'>
              <button id='1' ref={buttonCopyRef} onClick={handleClickToCopy}>{copiedC1 ? copiedContent[0] : copyContent[0]}</button>
              <div className="mainCurrencyContent">
                <input type="number" id="inputC1" defaultValue={1} ref={inputC1Ref} onChange={handleInputC1}/>
                <select name="coins" id="currency1" ref={currency1Ref} onChange={handleSelectChange}>
                  <option value="USD">Dólar Americano</option>
                  {
                    coins.filter(coin => coin.value != "USD").map((coin)=>(<option key={coin.value} value={coin.value}>{coin.label}</option>))
                  }
                </select>
              </div>
            </div>
            {arrowImage}
            <div className="currency" id='currency2'>
              <button id='2' ref={buttonCopyRef} onClick={handleClickToCopy}>{copiedC2 ?  copiedContent[1] : copyContent[1]}</button>
              <div className="mainCurrencyContent">
                <input type="number" id="inputC2" ref={inputC2Ref} onChange={handleInputC2}/>
                <select name="coins" id="currency2" ref={currency2Ref} onChange={handleSelectChange}>
                  <option value="BRL">Real Brasileiro</option>
                  {
                    coins.filter(coin => coin.value != "BRL").map((coin)=>(<option key={coin.value} value={coin.value}>{coin.label}</option>))
                  }
                </select>
              </div>
            </div>
        </div>
        <p id='apiAuthor'>Data by <a href='https://docs.awesomeapi.com.br/' target='_blank' rel='noopener noreferrer'>Awesome API</a></p>
      </div>
    </div>
  )
}

export default Converter