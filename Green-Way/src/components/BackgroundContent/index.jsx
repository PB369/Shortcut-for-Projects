import Converter from '../Converter'
import './css/BackgroundContent.scss'

const BackgroundContent = () => {
  return (
    <main>
      <div className="background">
        <h2>Seu jeito fácil de converter moedas.</h2>
        <Converter/>
      </div>
    </main>
  )
}

export default BackgroundContent