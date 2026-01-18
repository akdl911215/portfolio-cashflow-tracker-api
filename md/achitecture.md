portfolio-cashflow-tracker-api/
├─ build.gradle.kts
├─ settings.gradle.kts
└─ src/
├─ main/
│  ├─ kotlin/
│  │  └─ com/yourname/portfolio/
│  │     ├─ PortfolioApplication.kt
│  │     ├─ config/
│  │     │  ├─ JacksonConfig.kt
│  │     │  ├─ JpaConfig.kt
│  │     │  └─ WebConfig.kt
│  │     ├─ common/
│  │     │  ├─ error/
│  │     │  │  ├─ ErrorCode.kt
│  │     │  │  ├─ BusinessException.kt
│  │     │  │  └─ GlobalExceptionHandler.kt
│  │     │  ├─ time/
│  │     │  │  ├─ ClockProvider.kt
│  │     │  │  └─ SystemClockProvider.kt
│  │     │  ├─ util/
│  │     │  └─ paging/
│  │     └─ feature/
│  │        ├─ account/
│  │        │  ├─ domain/
│  │        │  │  ├─ Account.kt
│  │        │  │  └─ AccountType.kt
│  │        │  ├─ application/
│  │        │  │  ├─ RegisterAccountUseCase.kt
│  │        │  │  ├─ UpdateAccountUseCase.kt
│  │        │  │  ├─ DeleteAccountUseCase.kt
│  │        │  │  └─ GetAccountsQuery.kt
│  │        │  └─ adapter/
│  │        │     ├─ in/web/
│  │        │     │  ├─ AccountController.kt
│  │        │     │  ├─ dto/
│  │        │     │  │  ├─ AccountRequest.kt
│  │        │     │  │  └─ AccountResponse.kt
│  │        │     │  └─ mapper/
│  │        │     └─ out/persistence/
│  │        │        ├─ AccountJpaEntity.kt
│  │        │        ├─ AccountJpaRepository.kt
│  │        │        └─ AccountRepositoryAdapter.kt
│  │        │
│  │        ├─ asset/
│  │        │  ├─ domain/
│  │        │  │  ├─ Asset.kt
│  │        │  │  ├─ AssetClass.kt
│  │        │  │  └─ Ticker.kt
│  │        │  ├─ application/
│  │        │  │  ├─ RegisterAssetUseCase.kt
│  │        │  │  ├─ UpdateAssetUseCase.kt
│  │        │  │  ├─ DeleteAssetUseCase.kt
│  │        │  │  └─ GetAssetsQuery.kt
│  │        │  └─ adapter/
│  │        │     ├─ in/web/
│  │        │     │  ├─ AssetController.kt
│  │        │     │  └─ dto/
│  │        │     └─ out/persistence/
│  │        │        ├─ AssetJpaEntity.kt
│  │        │        ├─ AssetJpaRepository.kt
│  │        │        └─ AssetRepositoryAdapter.kt
│  │        │
│  │        ├─ holding/
│  │        │  ├─ domain/
│  │        │  │  ├─ Holding.kt
│  │        │  │  └─ Quantity.kt
│  │        │  ├─ application/
│  │        │  │  ├─ UpsertHoldingUseCase.kt
│  │        │  │  └─ GetHoldingsQuery.kt
│  │        │  └─ adapter/
│  │        │     ├─ in/web/
│  │        │     │  ├─ HoldingController.kt
│  │        │     │  └─ dto/
│  │        │     └─ out/persistence/
│  │        │        ├─ HoldingJpaEntity.kt
│  │        │        ├─ HoldingJpaRepository.kt
│  │        │        └─ HoldingRepositoryAdapter.kt
│  │        │
│  │        ├─ price/
│  │        │  ├─ domain/
│  │        │  │  ├─ PriceSnapshot.kt
│  │        │  │  ├─ Price.kt
│  │        │  │  └─ PriceDate.kt
│  │        │  ├─ application/
│  │        │  │  ├─ RecordPriceSnapshotUseCase.kt
│  │        │  │  ├─ ImportPriceSnapshotsUseCase.kt
│  │        │  │  ├─ GetLatestPricesQuery.kt
│  │        │  │  └─ GetPriceHistoryQuery.kt
│  │        │  └─ adapter/
│  │        │     ├─ in/web/
│  │        │     │  ├─ PriceController.kt
│  │        │     │  ├─ PriceImportController.kt
│  │        │     │  └─ dto/
│  │        │     └─ out/persistence/
│  │        │        ├─ PriceSnapshotJpaEntity.kt
│  │        │        ├─ PriceSnapshotJpaRepository.kt
│  │        │        └─ PriceRepositoryAdapter.kt
│  │        │
│  │        ├─ ledger/
│  │        │  ├─ domain/
│  │        │  │  ├─ LedgerEntry.kt
│  │        │  │  ├─ LedgerType.kt
│  │        │  │  ├─ Money.kt
│  │        │  │  └─ LedgerDate.kt
│  │        │  ├─ application/
│  │        │  │  ├─ RecordLedgerEntryUseCase.kt
│  │        │  │  ├─ ImportLedgerUseCase.kt
│  │        │  │  ├─ GetLedgerQuery.kt
│  │        │  │  └─ GetCashflowSummaryQuery.kt
│  │        │  └─ adapter/
│  │        │     ├─ in/web/
│  │        │     │  ├─ LedgerController.kt
│  │        │     │  ├─ LedgerImportController.kt
│  │        │     │  └─ dto/
│  │        │     └─ out/persistence/
│  │        │        ├─ LedgerJpaEntity.kt
│  │        │        ├─ LedgerJpaRepository.kt
│  │        │        └─ LedgerRepositoryAdapter.kt
│  │        │
│  │        ├─ dashboard/
│  │        │  ├─ domain/
│  │        │  │  ├─ Dashboard.kt
│  │        │  │  ├─ Allocation.kt
│  │        │  │  ├─ Performance.kt
│  │        │  │  └─ Yield.kt
│  │        │  ├─ application/
│  │        │  │  ├─ GetDashboardQuery.kt
│  │        │  │  ├─ GetAllocationQuery.kt
│  │        │  │  ├─ GetDividendSummaryQuery.kt
│  │        │  │  └─ GetPerformanceQuery.kt
│  │        │  └─ adapter/
│  │        │     └─ in/web/
│  │        │        ├─ DashboardController.kt
│  │        │        └─ dto/
│  │        │
│  │        └─ import/            # v0.1: CSV 공통만(선택)
│  │           ├─ application/
│  │           │  ├─ CsvParser.kt
│  │           │  ├─ CsvSchema.kt
│  │           │  └─ ImportReport.kt
│  │           └─ adapter/
│  │              └─ in/web/
│  │                 ├─ ImportController.kt
│  │                 └─ dto/
│  │
│  └─ resources/
│     ├─ application.yml
│     └─ db/migration/            # (선택) Flyway 쓸 경우
│
└─ test/
└─ kotlin/
└─ com/yourname/portfolio/
└─ feature/...
