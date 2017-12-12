package jp.digitalrabbit.kancolleutility.http

import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/**
 * 遠征に関連する情報の取得用 API 群
 *
 * @author Digital Rabbit
 * @since 2017/12/12.
 */


fun getExpeditionInformation(): Single<String> {
    return Single.create { emitter ->
        val req: Request = Request.Builder()
                .url("http://wikiwiki.jp/kancolle/?%B1%F3%C0%AC")
                .get()
                .build()

        val client = OkHttpClient()
        try {
            val res: Response = client.newCall(req).execute()
            if (!emitter.isDisposed) {
                emitter.onSuccess(res.body()?.string() ?: "null")
            }
        } catch (ex: IOException) {
            if (!emitter.isDisposed) {
                emitter.onError(ex)
            }
        }
    }
}