import React, { Component } from 'react'
import { Trofeu } from './../../../assets'
import { Button, Ranking } from './..'

import './FinalJogo.css'

export class FinalJogo extends Component {

  render() {
    return (
      <div className="container-final-jogo" >
        <div className="div-gif-final-jogo">
          <div className="div-score">
            <p>PONTUAÇÃO</p>
            <div className="botao-score">
              {this.props.pontos} pts
            </div>
          </div>
        </div>
        <div className="container-muro-tela-final">
          <div className="buraco-tela-final">
            <div className="ranking-titulo">
              <img src={Trofeu} alt="trofeu" />
              Top 10
                </div>
            <div className="div-container-ranking" id="scroll-ranking">
              <Ranking classeTamanho="ranking-tamanho">
              </Ranking>
            </div>
          </div>
          <div className="final-jogo-botoes">
            <Button message="JOGAR" classeCor="button-cor-vermelha" onClick={this.props.onJogar} />
            <Button message="VOLTAR" classeCor="button-cor-bege" onClick={this.props.onVoltar}/>
          </div>
        </div>
      </div>
    )
  }

}
