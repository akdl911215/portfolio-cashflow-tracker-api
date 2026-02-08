package jay.chis.portfolio.cashflow.tracker.feature.asset.adapter.`in`.web

import jakarta.validation.Valid
import jay.chis.portfolio.cashflow.tracker.feature.asset.adapter.`in`.web.dto.AssetResponse
import jay.chis.portfolio.cashflow.tracker.feature.asset.adapter.`in`.web.dto.CreateAssetRequest
import jay.chis.portfolio.cashflow.tracker.feature.asset.adapter.`in`.web.dto.UpdateAssetRequest
import jay.chis.portfolio.cashflow.tracker.feature.asset.application.CreateAssetUseCase
import jay.chis.portfolio.cashflow.tracker.feature.asset.application.DeleteAssetUseCase
import jay.chis.portfolio.cashflow.tracker.feature.asset.application.GetAssetsQuery
import jay.chis.portfolio.cashflow.tracker.feature.asset.application.UpdateAssetUseCase
import jay.chis.portfolio.cashflow.tracker.feature.asset.domain.Asset
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@Validated
@RequestMapping("/api/users/{userId}/assets")
class AssetController(
    private val createAssetUseCase: CreateAssetUseCase,
    private val getAssetsQuery: GetAssetsQuery,
    private val updateAssetUseCase: UpdateAssetUseCase,
    private val deleteAssetUseCase: DeleteAssetUseCase
) {
    @PostMapping
    fun create(
        @PathVariable userId: UUID,
        @RequestBody @Valid req: CreateAssetRequest
    ): ResponseEntity<AssetResponse> {
        val created = createAssetUseCase.create(
            userId = userId,
            symbol = req.symbol,
            name = req.name,
            assetClass = req.assetClass,
            currency = req.currency
        )
        return ResponseEntity.status(HttpStatus.CREATED).body(created.toResponse())
    }

    @GetMapping
    fun getAll(@PathVariable userId: UUID): ResponseEntity<List<AssetResponse>> {
        val assets = getAssetsQuery.getAll(userId)
        return ResponseEntity.ok(assets.map { it.toResponse() })
    }

    @GetMapping("/{assetId}")
    fun getById(
        @PathVariable userId: UUID,
        @PathVariable assetId: UUID
    ): ResponseEntity<AssetResponse> {
        val asset = getAssetsQuery.getById(userId, assetId)
        return ResponseEntity.ok(asset.toResponse())
    }

    @PutMapping("/{assetId}")
    fun update(
        @PathVariable userId: UUID,
        @PathVariable assetId: UUID,
        @RequestBody @Valid req: UpdateAssetRequest
    ): ResponseEntity<AssetResponse> {
        val updated = updateAssetUseCase.update(
            userId = userId,
            assetId = assetId,
            name = req.name,
            assetClass = req.assetClass,
            currency = req.currency
        )
        return ResponseEntity.ok(updated.toResponse())
    }

    @DeleteMapping("/{assetId}")
    fun delete(
        @PathVariable userId: UUID,
        @PathVariable assetId: UUID
    ): ResponseEntity<Void> {
        deleteAssetUseCase.delete(userId, assetId)
        return ResponseEntity.noContent().build()
    }

    private fun Asset.toResponse(): AssetResponse =
        AssetResponse(
            id = this.id,
            userId = this.userId,
            symbol = this.symbol,
            name = this.name,
            assetClass = this.assetClass,
            currency = this.currency
        )
}





