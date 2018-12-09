import React, { Component } from 'react'
import toastr from 'toastr'
import { Redirect } from 'react-router-dom'
import { UsuarioService, MuroService, PartidaService } from './../../../service'
import { DIFICULDADES } from './../../../enums'
import { Pandeiro, Errou, Acertou, Trofeu, Punk } from './../../../assets'
import { Base, Ranking, Button, FinalJogo, Vida } from './../../components'

import './Jogo.css'

export class Jogo extends Component {

    constructor(props) {
        super(props)

        this.usuarioService = new UsuarioService();

        this.estadoInicialAnimacoes = {
            muroAndando: false,
            punkPichando: true,
            formatoStick: false,
            sinaleiraAmarela: false,
            sinaleiraVerde: false,
            sinaleiraVermelha: false
        }

        this.state = {
            mensagemToast: '',
            mensagemDeErro: '',
            pontos: 0,
            vidas: '',
            stick: '',
            deveRedirecionarParaLogin: !this.usuarioService.getToken(),
            deveRedirecionarParaTutorial: false,
            deveRedirecionarParaIniciarPartida: false,
            deveRedirecionarParaFinalPartida: false,
            dificuldade: DIFICULDADES.FACIL
        }

        this.audio = new Audio(Pandeiro)
        this.audioErro = new Audio(Errou)
        this.audioAcerto = new Audio(Acertou)
        this.video = document.createElement('video')
        this.canvas = document.createElement('canvas')
        this.pictureDevice = null
        this.muroService = new MuroService()
        this.partidaService = new PartidaService()
        this.usuarioService = new UsuarioService()

        this.mudarAnimacaoMuro = this.mudarAnimacaoMuro.bind(this)
        this.mudarPunk = this.mudarPunk.bind(this)
        this.mudarStick = this.mudarStick.bind(this)
        this.mudarSinaleiraAmarela = this.mudarSinaleiraAmarela.bind(this)
        this.mudarSinaleiraVerde = this.mudarSinaleiraVerde.bind(this)
        this.mudarSinaleiraVermelha = this.mudarSinaleiraVermelha.bind(this)
    }

    componentDidMount() {
        toastr.options = {
            "closeButton": false,
            "debug": false,
            "newestOnTop": true,
            "progressBar": true,
            "positionClass": "toast-bottom-center",
            "preventDuplicates": true,
            "onclick": null,
            "showDuration": "300",
            "hideDuration": "1000",
            "timeOut": "5000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        }

        document.title = "Hole In The Wall"

        if (navigator.mediaDevices) {
            navigator.mediaDevices.getUserMedia({ video: true })
                .then(stream => {
                    this.videoStream = stream;
                })
                .catch(error => {
                    document.body.textContent = 'Não foi possivel acessar a camera.';
                })
        }

    }

    tirarFoto = () => {
        this.pictureDevice.takePhoto()
            .then(blob => createImageBitmap(blob))
            .then(imageBitmap => {
                this.manipularFoto(imageBitmap);
            })
    }

    manipularFoto = (imageBitmap) => {
        const width = 500;

        const height = 375;

        this.canvas.width = width;

        this.canvas.height = height;

        const context = this.canvas.getContext('2d');

        context.translate(width, 0);
        context.scale(-1, 1);
        context.drawImage(imageBitmap, 0, 0, width, height);

        this.uploadDaFoto(this.canvas.toDataURL('image/png'));
        setTimeout(this.props.toggleLoading, 4000)
    }

    uploadDaFoto = (base64) => {
        this.muroService.uploadFoto(base64).then((response) => {
            this.props.toggleLoading()
            this.setState({
                pontos: response.data.pontos,
                vidas: response.data.vidas,
                stick: response.data.stickmanBase64
            })

            if (this.state.vidas <= 0) {
                this.audio.pause()
                this.setState({
                    deveRedirecionarParaFinalPartida: true,
                    deveRedirecionarParaIniciarPartida: false,
                })
            } else {
                if (response.data.passou) {
                    this.audioAcerto.play()
                    this.mudarSinaleiraVerde()
                    setTimeout(this.mudarSinaleiraVerde, 2000)
                } else if (response.data.passou === false) {
                    this.audioErro.play()
                    this.mudarSinaleiraVermelha()
                    setTimeout(this.mudarSinaleiraVermelha, 2000)
                }
                this.resetarAnimacoesETirarFoto()
            }
        }).catch((err) => {
            toastr.warning(err.response ? err.response.data.message : err.message)
            this.resetarAnimacoesETirarFoto()
        })

    }

    resetarAnimacoesETirarFoto() {
        this.mudarPunk()
        this.mudarAnimacaoMuro()
        this.mudarStick()
        this.mudarSinaleiraAmarela()
        setTimeout(this.mudarPunk, 3900)
        setTimeout(this.mudarStick, 3900)
        setTimeout(this.mudarAnimacaoMuro, 4000)
        setTimeout(this.mudarSinaleiraAmarela, 10000)
        setTimeout(this.tirarFoto, 10000)
    }

    mudarAnimacaoMuro() {
        this.setState({muroAndando: !this.state.muroAndando})
    }

    mudarPunk() {
        this.setState({punkPichando: !this.state.punkPichando})
    }

    mudarStick() {
        this.setState({formatoStick: !this.state.formatoStick})
    }

    mudarSinaleiraAmarela() {
        this.setState({sinaleiraAmarela: !this.state.sinaleiraAmarela})
    }

    mudarSinaleiraVermelha() {
        this.setState({sinaleiraVermelha: !this.state.sinaleiraVermelha})
    }

    mudarSinaleiraVerde() {
        this.setState({sinaleiraVerde: !this.state.sinaleiraVerde})
    }

    jogar = (e) => {
        e.preventDefault();
        this.playMusic();
        this.partidaService.iniciar(this.state.dificuldade).then((response) => {
            this.setState({
                vidas: response.data.vidas,
                stick: response.data.stickmanBase64,
                deveRedirecionarParaIniciarPartida: true,
                ...this.estadoInicialAnimacoes
            }, this.iniciarCamera)
            setTimeout(this.mudarPunk, 3900)
            setTimeout(this.mudarStick, 3900)
            setTimeout(this.mudarAnimacaoMuro, 4000)
            setTimeout(this.tirarFoto, 10000)
            setTimeout(this.mudarSinaleiraAmarela, 10000)
        })
    }

    iniciarCamera() {
        const video = document.querySelector('video');
        const videoDevice = this.videoStream.getVideoTracks()[0]
        this.pictureDevice = new ImageCapture(videoDevice)
        video.srcObject = this.videoStream;
    }

    playMusic() {
        this.audio.volume = 1;

        this.audio.play();

        this.audio.addEventListener('ended', function () {
            this.currentTime = 0;
            this.play();
        }, false);
    }

    handleChange = e => {
        const { name, value } = e.target

        this.setState({
            [name]: value
        })
    }

    onClickLogout = () => {
        this.usuarioService.removerToken();
        this.setState({
            deveRedirecionarParaLogin: true
        })
    }

    onClickTutorial = () => {
        this.setState({
            deveRedirecionarParaTutorial: true
        })
    }

    form() {
        const { dificuldade } = this.state

        return (
            <form className="selecao-dificuldade-formulario" onSubmit={this.jogar}>
                <p className="jogo-titulo"> Dificuldade </p>
                <div className="jogo-selecao-dificuldade">
                    <label className="radio-nivel">
                        <input onChange={this.handleChange} checked={dificuldade === DIFICULDADES.FACIL} type="radio" name="dificuldade" value={DIFICULDADES.FACIL} />
                        <span>Fácil</span>
                        <div className="btn-img-punk">
                            <img src={Punk} alt="punk decorativo" />
                        </div>
                    </label>
                    <label className="radio-nivel">
                        <input onChange={this.handleChange} checked={dificuldade === DIFICULDADES.MEDIO} type="radio" name="dificuldade" value={DIFICULDADES.MEDIO} />
                        <span>Médio</span>
                        <div className="btn-img-punk">
                            <img src={Punk} alt="punk decorativo" />
                        </div>
                    </label>
                    <label className="radio-nivel">
                        <input onChange={this.handleChange} checked={dificuldade === DIFICULDADES.DIFICIL} type="radio" name="dificuldade" value={DIFICULDADES.DIFICIL} />
                        <span>Difícil</span>
                        <div className="btn-img-punk">
                            <img src={Punk} alt="punk decorativo" />
                        </div>
                    </label>
                    <label className="radio-nivel">
                        <input onChange={this.handleChange} checked={dificuldade === DIFICULDADES.HARDCORE} type="radio" name="dificuldade" value={DIFICULDADES.HARDCORE} />
                        <span>Hardcore</span>
                        <div className="btn-img-punk">
                            <img src={Punk} alt="punk decorativo" />
                        </div>
                    </label>
                </div>
                <div className="jogar-botao">
                    <Button message="JOGAR" classeCor="button-cor-vermelha"/>
                </div>
                <div className="tutorial-botao">
                    <Button message="TUTORIAL" classeCor="button-cor-vermelha" onClick={this.onClickTutorial}/>
                </div>
                <div className="logout-botao">
                    <Button message="SAIR" classeCor="button-cor-bege" onClick={this.onClickLogout}/>
                </div>
            </form>
        )
    }

    renderInicioDeJogo() {
        const { muroAndando, punkPichando, formatoStick, sinaleiraAmarela, sinaleiraVerde, sinaleiraVermelha } = this.state

        return (
            <div className="partida">
                <div className={`sinaleira sinaleira-vermelha-1 ${sinaleiraVermelha ? '' : 'hidden'}`} />
                <div className={`sinaleira sinaleira-vermelha-2 ${sinaleiraVermelha ? '' : 'hidden'}`} />
                <div className={`sinaleira sinaleira-amarela-1 ${sinaleiraAmarela ? '' : 'hidden'}`} />
                <div className={`sinaleira sinaleira-amarela-2 ${sinaleiraAmarela ? '' : 'hidden'}`} />
                <div className={`sinaleira sinaleira-verde-1 ${sinaleiraVerde ? '' : 'hidden'}`} />
                <div className={`sinaleira sinaleira-verde-2 ${sinaleiraVerde ? '' : 'hidden'}`} />
                <div className="div-score">
                    <p>PONTUAÇÃO</p>
                    <div className="botao-score">
                        {this.state.pontos} pts
                    </div>
                </div>
                <div className="coracao">
                    <Vida vidas={this.state.vidas - 1} />
                </div>
                <video autoPlay />
                <div className={`partida-muro ${muroAndando ? 'partida-muro-animacao': ''}`}>
                    <div className="partida-muro-stick hidden" style={{ backgroundImage: `url(${this.state.stick})` }}></div>
                    <div className={`partida-muro-stick ${formatoStick ? '' : 'hidden'}`} style={{ backgroundImage: `url(${this.state.stick})` }}></div>
                    <div className={`partida-muro-punk ${punkPichando ? '' : 'hidden'}`}></div>
                </div>
            </div>
        )
    }

    renderTutorial = () => {
        return (
            <div className="tutorial">
                <Button message="VOLTAR" onClick={this.voltar} classeCor="button-cor-vermelha tutorial-botao-tela"></Button>
            </div>
        )
    }

    renderFinalDeJogo = () => {
        return (
            <FinalJogo onJogar={this.jogar} onVoltar={this.voltar} pontos={this.state.pontos} />
        )
    }

    voltar = () => {
        this.setState({
            deveRedirecionarParaFinalPartida: false,
            deveRedirecionarParaIniciarPartida: false,
            deveRedirecionarParaLogin: false,
            deveRedirecionarParaTutorial: false
        })
    }

    limparMensagemDaToast = () => {
        this.setState({
            mensagemToast: ''
        })
    }

    render() {
        const { deveRedirecionarParaLogin, deveRedirecionarParaFinalPartida, deveRedirecionarParaIniciarPartida, deveRedirecionarParaTutorial } = this.state

        if (deveRedirecionarParaLogin) {
            return <Redirect to="/login" />
        }
        if(deveRedirecionarParaTutorial){
            return this.renderTutorial()
        }

        if (deveRedirecionarParaIniciarPartida) {
            return this.renderInicioDeJogo()
        } else {
            if (deveRedirecionarParaFinalPartida) {
                return this.renderFinalDeJogo()
            } else {
                return (
                    <Base form={this.form()} classeAdicional="jogo" containerMuroTamanho="tamanho-muro">
                        <div className="ranking-jogo">
                            <div className="ranking-titulo">
                                <img src={Trofeu} alt="trofeu" />
                                Top 10
                                </div>
                            <Ranking />
                        </div>
                    </Base>
                )

            }

        }

    }

}
