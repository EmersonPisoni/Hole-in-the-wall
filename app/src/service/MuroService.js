import axios from 'axios'
import BaseService from './BaseService'
import UsuarioService from './UsuarioService'

export class MuroService extends BaseService {

    constructor() {
        super()

        this.usuarioService = new UsuarioService()
    }

    uploadFoto(base64) {
        const token = this.usuarioService.getToken()

        const dados = {
            img: base64
        }

        return axios.post(`${this.baseUrl}muros`, dados, {
            loader: false,
            headers: {
                'Authorization': `${token}`
            }
        })
    }

}

export default MuroService
